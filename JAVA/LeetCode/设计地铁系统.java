package Java.LeetCode;

import java.util.*;

public class 设计地铁系统 {
    
    private static class UndergroundSystem{
        Map<String,List<int[]>> IN,OUT;
        UndergroundSystem(){
            this.IN=new HashMap<String,List<int[]>>();
            this.OUT=new HashMap<String,List<int[]>>();
        }
        void checkIn(int id, String stationName, int t){
            if(!IN.containsKey(stationName)){
                IN.put(stationName,new ArrayList<>(){{
                    add(new int[]{id,t});
                }});
            }else{
                IN.get(stationName).add(new int[]{id,t});
            }
        }
        void checkOut(int id, String stationName, int t){
            if(!OUT.containsKey(stationName)){
                OUT.put(stationName,new ArrayList<int[]>(){{
                    add(new int[]{id,t});
                }});
            }else{
                OUT.get(stationName).add(new int[]{id,t});
            }
        }
        double getAverageTime(String startStation, String endStation) {
            double sum=0;
            int count=0;
            for(int[] i:IN.get(startStation)){
                for(int[] j:OUT.get(endStation)){
                    if(i[0]==j[0]){
                        sum+=j[1]-i[1];
                        count++;
                    }
                }
            }
            return sum/count;
        }
    }
    public static void main(String[] args) {
        UndergroundSystem us=new UndergroundSystem();
        us.checkIn(45,"Leyton",3);
        us.checkIn(32,"Paradise",8);
        us.checkIn(27,"Leyton",10);
        us.checkOut(45,"Waterloo",15);
        us.checkOut(27,"Waterloo",20);
        us.checkOut(32,"Cambridge",22);
        System.out.println(us.getAverageTime("Paradise","Cambridge"));
        System.out.println(us.getAverageTime("Leyton","Waterloo"));
        us.checkIn(10,"Leyton",24);
        System.out.println(us.getAverageTime("Leyton","Waterloo"));
        us.checkOut(10,"Waterloo",38);
        System.out.println(us.getAverageTime("Leyton","Waterloo"));
    }
}
