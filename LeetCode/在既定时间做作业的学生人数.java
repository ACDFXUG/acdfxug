package LeetCode;

public class 在既定时间做作业的学生人数 {
    static int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int count=0;
        for(int i=0;i<startTime.length;i++){
            if(startTime[i]<=queryTime&&endTime[i]>=queryTime){
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args) {
        
    }
}
