package JAVA.Luogu;

import java.time.*;
import java.util.*;

public class 双十一 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int from=sc.nextInt(),to=sc.nextInt();
        int count=0;
        for(int year=from;year<=to;++year){
            var date=LocalDate.of(year,11,11);
            var weekday=date.getDayOfWeek();
            count+=switch(weekday){
                case SATURDAY,SUNDAY->1;
                default->0;
            };
        }
        System.out.println(count);
        sc.close();
    }
}
