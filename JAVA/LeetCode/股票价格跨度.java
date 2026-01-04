package Java.LeetCode;

import java.util.*;

public class 股票价格跨度 {
    private static class StockSpanner{
        List<Integer> stock;
        StockSpanner(){
            this.stock=new ArrayList<Integer>();
        }
        int next(int price){
            stock.add(price);
            int ans=0;
            for(int i=stock.size()-1;i>=0;i--){
                if(stock.get(i)<=price){
                    ans++;
                }else{
                    break;
                }
            }
            return ans;
        }
    }
    public static void main(String[] args) {
        StockSpanner ss=new StockSpanner();
        System.out.println(ss.next(100));
        System.out.println(ss.next(80));
        System.out.println(ss.next(60));
        System.out.println(ss.next(70));
        System.out.println(ss.next(60));
        System.out.println(ss.next(75));
        System.out.println(ss.next(85));
    }
}
