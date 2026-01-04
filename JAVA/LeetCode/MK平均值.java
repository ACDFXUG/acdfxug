package Java.LeetCode;


import java.util.*;

public class MK平均值 {
    private static class MKAverage{
        int m,k;
        List<Integer> mk;
        MKAverage(int m,int k){
            this.m=m;
            this.k=k;
            this.mk=new ArrayList<>();
        }
        void addElement(int num) {
            mk.add(num);
        }
        int calculateMKAverage() {
            if(mk.size()<m){
                return -1;
            }
            List<Integer> temp=mk
            .subList(mk.size()-m,mk.size());
            temp.sort(null);
            int sum=0;
            for(int i=k;i<temp.size()-k;i++){
                sum+=temp.get(i);
            }
            return sum/(m-2*k);
        }
    }
    public static void main(String[] args) {
        MKAverage mk=new MKAverage(3,1);
        mk.addElement(3);
        mk.addElement(1);
        System.out.println(mk.calculateMKAverage());
        mk.addElement(10);
        System.out.println(mk.calculateMKAverage());
        mk.addElement(5);
        mk.addElement(5);
        mk.addElement(5);
        System.out.println(mk.calculateMKAverage());
    }
}
