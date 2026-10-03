package Java.LeetCode;

import java.util.*;

public class 每隔n个顾客打折 {
    static class Cashier {
        int count=1;
        final int discount,n;
        int[] prices;
        Map<Integer,Integer> productIndex;
        public Cashier(int n, int discount, int[] products, int[] prices) {
            this.n=n;
            this.discount=discount;
            this.prices=prices;
            this.productIndex=new HashMap<Integer,Integer>(){{
                for(int i=0;i<products.length;++i){
                    put(products[i],i);
                }
            }};
        }
        
        public double getBill(int[] product, int[] amount) {
            if(count==n){
                count=1;
                double x=0;
                for(int i=0;i<product.length;++i){
                    x+=amount[i]*prices[productIndex.get(product[i])];
                }
                return x-(discount*x)/100;
            }else{
                ++count;
                double x=0;
                for(int i=0;i<product.length;++i){
                    x+=amount[i]*prices[productIndex.get(product[i])];
                }
                return x;
            }
        }
    }   
    public static void main(String[] args) {
        Cashier cas=new Cashier(3,50,
            new int[]{1,2,3,4,5,6,7},
            new int[]{100,200,300,400,300,200,100});
    }
}
