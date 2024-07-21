package LeetCode;

import java.util.*;

public class 重新排列日志文件 {
    private static class LogFile implements Comparable<LogFile>{
        String logs;
        String id;
        String content;
        LogFile(String log){
            this.logs=log;
            this.id=log.substring(0,log.indexOf(" "));
            this.content=log.substring(log.indexOf(" ")+1);
        }
        boolean isLetterLog(){
            char letter=content.charAt(0);
            return letter>='a'&&letter<='z';
        }
        boolean isDigitLog(){
            return !isLetterLog();
        }
        public String toString(){
            return logs;
        }
        public int compareTo(LogFile lf){
            if(isLetterLog()&&lf.isDigitLog()){
                return -1;
            }else if(isDigitLog()&&lf.isLetterLog()){
                return 1;
            }else if(isLetterLog()&&lf.isLetterLog()){
                int cmp=content.compareTo(lf.content);
                return cmp==0?id.compareTo(lf.id):cmp;
            }else{
                return 0;
            }
        }
    }
    static String[] reorderLogFiles(String[] logs) {
        LogFile[] LF=new LogFile[logs.length];
        for(int i=0;i<logs.length;i++){
            LF[i]=new LogFile(logs[i]);
        }
        Arrays.sort(LF);
        String[] ans=new String[logs.length];
        for(int i=0;i<logs.length;i++){
            ans[i]=LF[i].logs;
        }
        return ans;
        // List<LogFile> LF=new ArrayList<>(){{
        //     for(String log:logs){
        //         add(new LogFile(log));
        //     }
        // }};
        // return LF.stream()
        // .sorted()
        // .map(LogFile::toString)
        // .toArray(String[]::new);
    }
    public static void main(String[] args) {
        String[] logs={
            "dig1 8 1 5 1","let1 art can",
            "dig2 3 6","let2 own kit dig",
            "let3 art zero"
        };
        String[] ans=reorderLogFiles(logs);
        for(String s:ans){
            System.out.println(s);
        }
    }
}
