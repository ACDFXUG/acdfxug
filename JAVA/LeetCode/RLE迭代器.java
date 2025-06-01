package JAVA.LeetCode;

public class RLE迭代器 {
    private static class RLEIterator{
        final int[] rle;
        RLEIterator(int[] encoding){
            this.rle=encoding.clone();
        }
        int next(int n){
            for(int i=0;i<rle.length;i+=2){
                if(rle[i]==0){
                    continue;
                }
                if(n<=rle[i]){
                    rle[i]-=n;
                    return rle[i+1];
                }else{
                    n-=rle[i];
                    rle[i]=0;
                }
            }
            return -1;
        }
    }
    public static void main(String[] args) {
        int[] encoding={3,8,0,9,2,5};
        RLEIterator rleIter=new RLEIterator(encoding);
        System.out.println(rleIter.next(2));
        System.out.println(rleIter.next(1));
        System.out.println(rleIter.next(1));
        System.out.println(rleIter.next(2));
    }
}
