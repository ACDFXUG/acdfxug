package JAVA.LeetCode;

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
                    name.compareTo(food.name)
                    :food.rating-rating;
        }
        public int hashCode(){
            return Objects.hash(name,cuisine,rating);
        }
        public boolean equals(Object f){
            if(f==this){
                return true;
            }
            if(f==null){
                return false;
            }
            return f instanceof Food food
                &&food.name.equals(name)
                &&food.cuisine.equals(cuisine)
                &&food.rating==rating;
        }
        void setRating(int rating){
            this.rating=rating;
        }
    }
    private static class FoodRatings{
        HashMap<String,Food> Foods;
        TreeSet<Food> foodName;
        FoodRatings(String[] foods,String[] cuisines,int[] ratings) {
            this.Foods=new HashMap<String,Food>();
            this.foodName=new TreeSet<Food>();
            for(int i=0;i<foods.length;i++){
                var tmp=new Food(foods[i],cuisines[i],ratings[i]);
                Foods.put(foods[i],tmp);
                foodName.add(tmp);
            }
        }
        void changeRating(String food,int newRating) {
            var old=Foods.get(food);
            foodName.remove(old);
            old.setRating(newRating);
            foodName.add(old);
        }
        String highestRated(String cuisine) {
            for(var food:foodName){
                if(food.cuisine.equals(cuisine)){
                    return food.name;
                }
            }
            return null;
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
