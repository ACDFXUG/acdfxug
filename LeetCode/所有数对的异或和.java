package LeetCode;

public class 所有数对的异或和 {
    static int xorAllNums(int[] nums1, int[] nums2) {
        final int m=nums1.length,n=nums2.length;
        int xorA=0,xorB=0;
        for(final int x:nums1) xorA^=x;
        for(final int x:nums2) xorB^=x;
        final int evenM=m&1,evenN=n&1;
        return (xorA*evenN)^(xorB*evenM);
    }
    public static void main(String[] args) {
        int[] nums1={1,2};
        int[] nums2={3,4};
        System.out.println(xorAllNums(nums1, nums2));
        
    }
}
