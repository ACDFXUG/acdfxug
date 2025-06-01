// package Luogu;

// import java.time.*;
// import java.time.format.*;
// import java.util.*;

// public class 刷题 {
//     static final DateTimeFormatter DTF=DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm");
//     public static void main(String[] args) {
//         Scanner sc=new Scanner(System.in);
//         Queue<Integer> que=new PriorityQueue<>();
//         int N=sc.nextInt(),ans=0;
//         while(N-->0) que.add(sc.nextInt());
//         var start=LocalDateTime.parse(sc.next(),DTF);
//         var end=LocalDateTime.parse(sc.next(),DTF);
//         sc.close();
//         var minutes=Duration.between(start,end).toMinutes();
//         System.out.println(minutes);
//         for(;minutes>0&&!que.isEmpty();){
//             var t=que.poll();
//             if(t<=minutes){
//                 ++ans;
//                 minutes-=t;
//             }
//         }
//         System.out.println(ans);
//     }
// }
package JAVA.Luogu;

import java.time.*;
import java.time.format.*;
import java.util.*;

public class 刷题 {
    static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm");
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue<Integer> que = new PriorityQueue<>();
        int N = sc.nextInt(), ans = 0;
        while (N-- > 0) que.add(sc.nextInt());
        
        // 手动处理0000年
        String startStr = sc.next();
        String endStr = sc.next();
        sc.close();
        
        LocalDateTime start = parseWithZeroYear(startStr);
        LocalDateTime end = parseWithZeroYear(endStr);
        
        var minutes = Duration.between(start, end).toMinutes();
        System.out.println(minutes);
        
        for (; minutes > 0 && !que.isEmpty(); ) {
            var t = que.poll();
            if (t <= minutes) {
                ++ans;
                minutes -= t;
            }
        }
        System.out.println(ans);
    }
    
    private static LocalDateTime parseWithZeroYear(String dateTimeStr) {
        if (dateTimeStr.startsWith("0000-")) {
            // 将0000替换为0001，然后解析
            String adjusted = "0001" + dateTimeStr.substring(4);
            LocalDateTime dt = LocalDateTime.parse(adjusted, DTF);
            // 减去1年
           return dt.minusYears(1);
        }
        return LocalDateTime.parse(dateTimeStr, DTF);
    }
}
