package Java.LeetCode;

import java.util.*;

public class 餐厅过滤器 {
    private static class Restaurant{
        final int id,rating,veganFriendly,price,distance;
        Restaurant(int... rest){
            this.id=rest[0];
            this.rating=rest[1];
            this.veganFriendly=rest[2];
            this.price=rest[3];
            this.distance=rest[4];
        }
    }
    static List<Integer> 
    filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        List<Restaurant> res=new ArrayList<Restaurant>(){{
            for(int i=0;i<restaurants.length;i++){
                add(new Restaurant(restaurants[i]));
            }
        }};
        return res.stream().filter(R->{
            return R.veganFriendly>=veganFriendly
            &&R.price<=maxPrice
            &&R.distance<=maxDistance;
        }).sorted((R1,R2)->{
            return R1.rating==R2.rating?
            R2.id-R1.id:R2.rating-R1.rating;
        }).map(R->R.id).toList();
    }
    public static void main(String[] args) {
        
    }
}
