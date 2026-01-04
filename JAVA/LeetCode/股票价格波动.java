package Java.LeetCode;

import java.util.*;

@SuppressWarnings("unused")
public class 股票价格波动 {
    private static record Pair(int first,int second){
        public int hashCode(){
            int res=1;
            res=31*res+first;
            res=31*res+second;
            return res;
        }
        public boolean equals(Object p){
            if(this==p){
                return true;
            }
            if(p==null){
                return false;
            }
            return p instanceof Pair pair
                &&first==pair.first&&second==pair.second;
        }
    }
    private static class StockPrice{
        TreeSet<Pair> prices;
        TreeMap<Integer,Integer> time;
        StockPrice(){
            this.prices=new TreeSet<Pair>((p1,p2)->p1.second()-p2.second());
            this.time=new TreeMap<Integer,Integer>();
        }
        void update(int timestamp, int price) {
            if(time.containsKey(timestamp)){
                int oldPrice=time.get(timestamp);
                prices.remove(new Pair(timestamp,oldPrice));
                prices.add(new Pair(timestamp,price));
                time.put(timestamp,price);
            }else{
                prices.add(new Pair(timestamp,price));
                time.put(timestamp,price);
            }
        }
        int current() {
            return time.lastEntry().getValue();
        }
        int maximum() {
            return prices.last().second();
        }
        int minimum() {
            return prices.first().second();
        }
    }
    public static void main(String[] args) {
        
    }
}
