package LeetCode;

import java.util.*;

public class 设计食物评分系统 {
    private static final class Food
    implements Comparable<Food>{
        final String name,cuisine;
        int rating;
        Food(String name,String cuisine,int rating){
            this.name=name;
            this.cuisine=cuisine;
            this.rating=rating;
        }
        public int compareTo(Food food){
            return rating==food.rating?
            name.compareTo(food.name):food.rating-rating;
        }
        void setRating(int rating){
            this.rating=rating;
        }
    }
    private static class FoodRatings{
        Map<String,Food> FOOD;
        FoodRatings(String[] foods,String[] cuisines,int[] ratings) {
            int n=foods.length;
            this.FOOD=new HashMap<String,Food>(){{
                for(int i=0;i<n;i++){
                    Food temp=new Food(
                        foods[i],
                        cuisines[i],
                        ratings[i]
                    );
                    put(foods[i],temp);
                }
            }};
        }
        void changeRating(String food,int newRating) {
            FOOD.get(food).setRating(newRating);
        }
        String highestRated(String cuisine) {
            return FOOD.values().stream()
            .filter(F->F.cuisine.equals(cuisine))
            .min(Food::compareTo)
            .get().name;
        }
    }
    public static void main(String[] args) {
        FoodRatings FR=new FoodRatings(
            new String[]{"kimchi","miso","sushi","moussaka","ramen","bulgogi"},
            new String[]{"korean","japanese","japanese","greek","japanese","korean"},
            new int[]{9,12,8,15,14,7}
        );
        System.out.println(FR.highestRated("korean"));
        System.out.println(FR.highestRated("japanese"));  
        FR.changeRating("sushi", 16);  
        System.out.println(FR.highestRated("japanese"));
        FR.changeRating("ramen", 16);
        System.out.println(FR.highestRated("japanese"));
    }
}
