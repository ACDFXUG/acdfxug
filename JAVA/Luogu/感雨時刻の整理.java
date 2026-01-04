package Java.Luogu;

import java.util.*;
import java.util.regex.*;

public class 感雨時刻の整理 {
    static final Pattern TIME=Pattern.compile("(\\d{2})(\\d{2})");
    private static class Time implements Comparable<Time>{
        int hour,minute;
        Time(int hour,int minute){
            this.hour=hour;
            this.minute=minute;
        }
        Time roundDown(){
            int ms=hour*60+minute;
            int round=(ms/5)*5;
            hour=round/60;
            minute=round-hour*60;
            return this;
        }
        Time roundUp(){
            int ms=hour*60+minute;
            int round=Math.ceilDiv(ms,5)*5;
            hour=round/60;
            minute=round-hour*60;
            return this;
        }
        public int compareTo(Time t){
            return hour==t.hour?minute-t.minute:hour-t.hour;
        }
        public String toString(){
            return String.format("%02d%02d",hour,minute);
        }
    }
    private static record TimeInterval(Time start,Time end)
    implements Comparable<TimeInterval>{
        public int compareTo(TimeInterval ti){
            int s=start.compareTo(ti.start);
            return s==0?end.compareTo(ti.end):s;
        }
        public String toString(){
            return start+"-"+end;
        }
        boolean hasIntersect(TimeInterval ti){
            return end.compareTo(ti.start)>=0;
        }
        TimeInterval intersect(TimeInterval ti){
            return new TimeInterval(start,end.compareTo(ti.end)<0?ti.end:end);
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        TimeInterval[] tis=new TimeInterval[N];
        for(int i=0;i<N;i++){
            Matcher m=TIME.matcher(sc.next());
            Time[] ts=new Time[2];
            for(int j=0;j<2;j++){
                m.find();
                ts[j]=new Time(Integer.parseInt(m.group(1)),
                        Integer.parseInt(m.group(2)));
            }
            tis[i]=new TimeInterval(ts[0].roundDown(),ts[1].roundUp());
        }
        Arrays.sort(tis);
        var ans=new ArrayList<TimeInterval>(){{
            add(tis[0]);
        }};
        for(int i=1;i<N;i++){
            if(ans.getLast().hasIntersect(tis[i])){
                ans.set(ans.size()-1,ans.getLast().intersect(tis[i]));
            }else{
                ans.add(tis[i]);
            }
        }
        ans.forEach(System.out::println);
        sc.close();
    }
}
