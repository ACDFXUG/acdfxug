package JAVA.LeetCode;

public class 得到目标值的最少行动次数 {
    static int minMoves(int target, int maxDoubles) {
        int act=0;
        if(maxDoubles==0) return target-1;
        while(target>1){
            if(maxDoubles>0&&target%2==0){
                target>>=1;
                --maxDoubles;
            }else{
                --target;
            }
            ++act;
        }
        return act;
    }
    public static void main(String[] args) {
        System.out.println(minMoves(10,4));
    }
}
