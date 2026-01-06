package Java.Learn.MultiThread.GrabRedEnvelopes;

import java.util.*;
import java.util.concurrent.*;

public class GrabMain {
    public static void main(String[] args) {
        Employee[] emps=new Employee[100];
        for(int i=1;i<=100;++i) emps[i-1]=new Employee(i);
        Map<Integer,Integer> map=new HashMap<>();
        List<FutureTask<Integer>> grab=new ArrayList<>();
        for(int i=0;i<100;++i){
            FutureTask<Integer> task=new FutureTask<>(emps[i]);
            grab.add(task);
            new Thread(task).start();
        }
        for(int i=0;i<100;++i){
            try{
                int cash=grab.get(i).get();
                map.put(emps[i].getId(),cash);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        System.out.println(map);
    }
}
