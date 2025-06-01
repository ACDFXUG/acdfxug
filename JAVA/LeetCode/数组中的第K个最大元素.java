package JAVA.LeetCode;

public class 数组中的第K个最大元素 {
    static int findKthLargest(int[] nums, int k) {
        int[] temp=new int[200001];
        for(int x:nums){
            temp[x+100000]++;
        }
        for(int i=temp.length-1;i>=0;i--){
            if(temp[i]>0){
                k-=temp[i];
            }else{
                continue;
            }
            if(k<=0){
                return i-100000;
            }
        }
        return 0;
    }
    public static void main(String[] args) {
        int[] a={3,2,3,1,2,4,5,5,6};
        System.out.println(findKthLargest(a,4));
    }
}
