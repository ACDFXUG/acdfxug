package JAVA.Luogu;

import java.util.*;
import java.time.*;
import java.time.temporal.*;

public class 历史行程 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int year=sc.nextInt();
        var firstDay=LocalDate.of(year,5,1);
        var firstSunday=firstDay.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        var secondSunday=firstSunday.plusDays(7);
        int days=secondSunday.getDayOfYear()-firstDay.getDayOfYear();
        System.out.println(days+1);
        sc.close();
    }
}
