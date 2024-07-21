package LeetCode;

import java.util.*;

public class FizzBuzz {
    public static void fizzbuzz(int n){
        List<String> ans=new ArrayList<>();
        ans.add(null);
        for(int i=1;i<=n;i++){
            if(i%3==0&&i%5==0){
                ans.add("FizzBuzz");
            }else if(i%3==0){
                ans.add("Fizz");
            }else if(i%5==0){
                ans.add("Buzz");
            }else{
                ans.add(i+"");
            }
        }
        for(int i=1;i<ans.size();i++){
            System.out.println(ans.get(i));
        }
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        fizzbuzz(n);
        sc.close();
    }
}
