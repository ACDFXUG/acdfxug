package Java.LeetCode;

public class 找到需要补充粉笔的学生编号 {
    static int chalkReplacer(int[] chalk, int k) {
        final int n=chalk.length;
        long sum=0;
        for(int i=0;i<n;sum+=chalk[i++]);
        k%=sum;
        for(int i=0;i<n;i++){
            if(chalk[i]>k){
                return i;
            }
            k-=chalk[i];
        }
        return -1;
    }
    public static void main(String[] args) {
        int chalk[]={3,4,1,2},k=25;
        System.out.println(chalkReplacer(chalk, k));
    }
}
