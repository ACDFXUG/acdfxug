package JAVA.LeetCode;

import java.util.*;

public class 设计电影租借系统 {
    private static class Shop{
        Map<Movie,Integer> notRent;
        Map<Movie,Integer> hasRent;
        int shopId;
        Shop(int shopId){
            this.shopId=shopId;
            this.notRent=new HashMap<>();
            this.hasRent=new HashMap<>();
        }
        public int hashCode(){
            return shopId;
        }
    }
    private static class Movie{
        int movieId;
        Map<Shop,Integer> notRentShops;
        Map<Shop,Integer> hasRentShops;
        Movie(int id){
            this.movieId=id;
            this.notRentShops=new HashMap<>();
            this.hasRentShops=new HashMap<>();
        }
        public int hashCode(){
            return movieId;
        }
    }
    private static class MovieRentingSystem{
        Map<Integer,Movie> movies;
        Map<Integer,Shop> shops;
        MovieRentingSystem(int n, int[][] entries) {
            this.movies=new HashMap<>();
            this.shops=new HashMap<>(n);
            for(int[] ent:entries){
                int shop=ent[0],movie=ent[1],price=ent[2];
                Movie mv=movies.computeIfAbsent(movie,id->new Movie(id));
                Shop sp=shops.computeIfAbsent(shop,id->new Shop(id));
                mv.notRentShops.put(sp,price);
                sp.notRent.put(mv,price);
            }
        }
        List<Integer> search(int movie) {
            if(!movies.containsKey(movie)) return new ArrayList<>();
            var shps=movies.get(movie).notRentShops;
            return shps.entrySet().stream()
                .sorted((e1,e2)->{
                    int p1=e1.getValue();
                    int p2=e2.getValue();
                    return p1==p2?
                        e1.getKey().shopId-e2.getKey().shopId:p1-p2;
                }).limit(5)
                .map(sp->sp.getKey().shopId)
                .toList();
        }
        void rent(int shop, int movie) {
            Shop sp=shops.get(shop);
            Movie mv=movies.get(movie);
            int p=sp.notRent.get(mv);
            sp.hasRent.put(mv,p);
            sp.notRent.remove(mv);
            mv.notRentShops.remove(sp);
            mv.hasRentShops.put(sp,p);
        }
        void drop(int shop, int movie) {
            Shop sp=shops.get(shop);
            Movie mv=movies.get(movie);
            int p=sp.hasRent.get(mv);
            sp.notRent.put(mv,p);
            sp.hasRent.remove(mv);
            mv.hasRentShops.remove(sp);
            mv.notRentShops.put(sp,p);
        }
        List<List<Integer>> report() {
            Map<Movie,Map.Entry<Shop,Integer>> cheapest=new HashMap<>();
            for(Movie mv:movies.values()){
                mv.hasRentShops.entrySet().stream()
                    .min((E1,E2)->{
                        int p1=E1.getValue(),p2=E2.getValue();
                        return p1==p2?E1.getKey().shopId-E2.getKey().shopId:p1-p2;
                    }).ifPresent(E->cheapest.put(mv,E));
            }
            return cheapest.entrySet().stream()
                .sorted((E1,E2)->{
                    int p1=E1.getValue().getValue(),p2=E2.getValue().getValue();
                    if(p1!=p2) return p1-p2;
                    else{
                        if(E1.getValue().getKey().shopId!=E2.getValue().getKey().shopId){
                            return E1.getValue().getKey().shopId-E2.getValue().getKey().shopId;
                        }else{
                            return E1.getKey().movieId-E2.getKey().movieId;
                        }
                    }
                }).limit(5)
                .map(E->{
                    List<Integer> list=new ArrayList<>();
                    list.add(E.getValue().getKey().shopId);
                    list.add(E.getKey().movieId);
                    return list;
                }).toList();
        }
    }
    public static void main(String[] args) {
        MovieRentingSystem mrs=new MovieRentingSystem(3,new int[][]{
            {0,1,5},
            {0,2,6},
            {0,3,7},
            {1,1,4},
            {1,2,7},
            {2,1,5}
        });
        System.out.println(mrs.search(1));
        mrs.rent(0,1);
        mrs.rent(1,2);
        System.out.println(mrs.report());
        mrs.drop(1,2);
        System.out.println(mrs.search(2));
    }
}
