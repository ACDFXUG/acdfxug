package JAVA.LeetCode;

import java.util.*;

public class 积压订单中的订单总数 {
    private static class Order
    implements Comparable<Order>{
        int price;
        int amount;
        int orderType;
        Order(int p,int a,int o){
            this.price=p;
            this.amount=a;
            this.orderType=o;
        }
        public int compareTo(Order o){
            return price-o.price;
        }
    }
    static int getNumberOfBacklogOrders(int[][] orders) {
        PriorityQueue<Order> buy=new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Order> sell=new PriorityQueue<>();
        for(var order:orders){
            Order cur=new Order(order[0],order[1],order[2]);
            switch(cur.orderType){
                case 0->{//buy
                    while(!sell.isEmpty()&&cur.price>=sell.peek().price){
                        var fst=sell.poll();
                        int minAmt=Math.min(cur.amount,fst.amount);
                        cur.amount-=minAmt;
                        fst.amount-=minAmt;
                        if(fst.amount>0) sell.add(fst);
                        if(cur.amount==0) break;
                    }
                    if(cur.amount>0) buy.add(cur);
                }case 1->{//sell
                    while(!buy.isEmpty()&&buy.peek().price>=cur.price){
                        var fst=buy.poll();
                        int minAmt=Math.min(cur.amount,fst.amount);
                        cur.amount-=minAmt;
                        fst.amount-=minAmt;
                        if(fst.amount>0) buy.add(fst);
                        if(cur.amount==0) break;
                    }
                    if(cur.amount>0) sell.add(cur);
                }
            }
        }
        long res=0;
        for(Order cur:sell) res+=cur.amount%1000000007;
        for(Order cur:buy) res+=cur.amount%1000000007;
        return (int)(res%1000000007);
    }
    public static void main(String[] args) {
        int[][] orders={
            {7,1000000000,1},
            {15,3,0},
            {5,999999995,0},
            {5,1,1}
        };
        System.out.println(getNumberOfBacklogOrders(orders));
    }
}
