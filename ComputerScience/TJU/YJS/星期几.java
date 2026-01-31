package ComputerScience.TJU.YJS;

import java.time.*;
import java.time.format.*;
import java.util.*;

public class 星期几 {
    public static void main(String[] args) {
        try(Scanner sc=new Scanner(System.in)){
            int T=sc.nextInt();
            while(T-->0){
                String date=sc.next();
                DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate ld=LocalDate.parse(date,dtf);
                // System.out.println(switch(ldt.getDayOfWeek()){
                //     case MONDAY->"Monday";
                //     case TUESDAY->"Tuesday";
                //     case WEDNESDAY->"Wednesday";
                //     case THURSDAY->"Thursday";
                //     case FRIDAY->"Friday";
                //     case SATURDAY->"Saturday";
                //     case SUNDAY->"Sunday";
                //     default->"Error";                
                // });
                switch(ld.getDayOfWeek()){
                    case MONDAY:System.out.println("Monday");break;
                    case TUESDAY:System.out.println("Tuesday");break;
                    case WEDNESDAY:System.out.println("Wednesday");break;
                    case THURSDAY:System.out.println("Thursday");break;
                    case FRIDAY:System.out.println("Friday");break;
                    case SATURDAY:System.out.println("Saturday");break;
                    case SUNDAY:System.out.println("Sunday");break;
                }
            }
        }
    }
}
