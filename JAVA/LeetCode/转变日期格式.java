package JAVA.LeetCode;

public class 转变日期格式 {
    static final String[] month={
        "Jan","Feb","Mar","Apr",
        "May","Jun","Jul","Aug",
        "Sep","Oct","Nov","Dec"
    };
    static String reformatDate(String date) {
        String[] time=date.split("\\s");
        String day=time[0].substring(0,time[0].length()-2);
        for(int i=0;i<12;i++){
            String m=month[i];
            if(m.equals(time[1])){
                return String.format("%s-%02d-%02d",
                time[2],i+1,Integer.parseInt(day));
            }
        }
        return null;
    }
    public static void main(String[] args) {
        
    }
}
