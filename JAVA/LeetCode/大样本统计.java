package Java.LeetCode;

public class 大样本统计 {
    static double[] sampleStats(int[] count) {
        int min=0,max=255;
        for(;min<256&&count[min]==0;min++);
        for(;max>=0&&count[max]==0;max--);
        int eleCnt=0;
        double sum=0;
        for(int i=0;i<256;i++){
            eleCnt+=count[i];
            sum+=((long)i)*count[i];
        }
        double mean=sum/eleCnt;
        double median=0;
        if((eleCnt&1)==1){
            int mid=eleCnt/2;
            int cnt=0;
            for(int i=0;i<256;i++){
                cnt+=count[i];
                if(cnt>mid){
                    median=i;
                    break;
                }
            }
        }else{
            int mid1 = eleCnt / 2 - 1, mid2 = eleCnt / 2;
            int cnt = 0;
            boolean foundMid1 = false;
            for (int i = 0; i < 256; i++) {
                cnt += count[i];
                if (!foundMid1 && cnt > mid1) {
                    median += i;
                    foundMid1 = true;
                }
                if (cnt > mid2) {
                    median += i;
                    break;
                }
            }
            median /= 2;
        }
        int mode=0;
        for(int i=0;i<256;i++){
            if(count[i]>count[mode]) mode=i;
        }
        return new double[]{min,max,mean,median,mode};
    }
    public static void main(String[] args) {

    }
}
