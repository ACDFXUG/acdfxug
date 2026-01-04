package Java.Learn.Collection.MovieManage.src;

import java.util.*;

public class MovieService {
    private static List<Movie> movies=new ArrayList<>();
    private static Scanner input=new Scanner(System.in);
    private void addMovie(){
        System.out.println("请输入电影名称");
        String name=input.next();
        System.out.println("请输入电影评分");
        double score=input.nextDouble();
        System.out.println("请输入主演");
        String actor=input.next();
        System.out.println("请输入价格");
        double price=input.nextDouble();
        Movie movie=new Movie(name,score,actor,price);
        movies.add(movie);
    }
    private void deleteMovie(){
        System.out.println("请输入要删除的电影名称");
        String name=input.next();
        for(int i=0;i<movies.size();i++){
            Movie movie=movies.get(i);
            if(movie.getName().equals(name)){
                movies.remove(i);
                System.out.println("删除成功");
                return;
            }
        }
    }
    private void queryMovie(){
        System.out.println("请输入要查询的电影名称");
        String name=input.next();
        for(Movie movie:movies){
            if(movie.getName().equals(name)){
                System.out.println(movie);
                return;
            }
        }
        System.out.println("没有此电影");
    }
    private void banActor(){
        System.out.println("请输入要封杀的演员");
        String actor=input.next();
        for(int i=0;i<movies.size();i++){
            Movie movie=movies.get(i);
            if(movie.getActor().equals(actor)){
                movies.remove(i);
                --i;
            }
        }
        System.out.println("封杀成功");
    }
    public void start(){
        for(;;){
            System.out.println("======电影管理系统======");
            System.out.println("1.添加电影");
            System.out.println("2.删除电影");
            System.out.println("3.查询电影");
            System.out.println("4.封杀某人");
            System.out.println("5.退出");

            System.out.println("请输入操作命令");
            switch(input.nextInt()){
                case 1->{
                    addMovie();
                    System.out.println("添加成功");
                }case 2->{
                    deleteMovie();
                }case 3->{
                    queryMovie();
                }case 4->{
                    banActor();
                }case 5->{
                    System.out.println("退出成功");
                    return;
                }default->{
                    System.out.println("输入错误");
                }
            }
        }
    }
    public static void main(String[] args) {
        MovieService ms=new MovieService();
        ms.start();
    }
}
