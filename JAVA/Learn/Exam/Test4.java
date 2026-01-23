package Java.Learn.Exam;

import java.util.*;

public class Test4 {
    public static void main(String[] args) {
        TreeMap<Liquid,Integer> order=new TreeMap<>();
        order.put(new Liquid("水",6),4);
        order.put(new Liquid("牛奶",20),8);
        order.put(new Liquid("五粮液",2000),2);
        order.put(new Liquid("可乐",18),6);
        order.put(new Liquid("茅台",4000),1);
        int total=10,val=0;
        for(var entry:order.entrySet()){
            Liquid lqd=entry.getKey();
            int amt=entry.getValue();
            if(total>=amt){
                System.out.println(lqd.name+" "+amt);
                total-=amt;
                val+=amt*lqd.value;
            }else{
                System.out.println(lqd.name+" "+total);
                val+=total*lqd.value;
                break;
            }
        }
        System.out.println("总价值:"+val+"元");
    }
}

class Liquid implements Comparable<Liquid>{
    String name;
    int value;
    Liquid(String name,int value){
        this.name=name;
        this.value=value;
    }
    public int compareTo(Liquid lqd){
        return lqd.value-value;
    }
}
