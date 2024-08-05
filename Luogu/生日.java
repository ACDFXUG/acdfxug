package Luogu;

import java.util.*;

public class 生日 {
    private static record Birthday(String name,int year,int month,int day) 
    implements Comparable<Birthday> {
        public int compareTo(Birthday other){
            if(year!=other.year){
                return year-other.year;
            }else if(month!=other.month){
                return month-other.month;
            }else if(day!=other.day){
                return day-other.day;
            }
            return 0;
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        Map<Integer,Birthday> rank=new HashMap<>();
        for(int i=0;i<n;i++){
            String name=sc.next();
            int year=sc.nextInt();
            int month=sc.nextInt();
            int day=sc.nextInt();
            rank.put(i,new Birthday(name,year,month,day));
        }
        rank.entrySet().stream()
        .sorted((E1,E2)->{
            Birthday b1=E1.getValue();
            Birthday b2=E2.getValue();
            int cmp=b1.compareTo(b2);
            return cmp==0?E2.getKey()-E1.getKey():cmp;
        }).forEach(E->System.out.println(E.getValue().name()));
        sc.close();
    }
}
