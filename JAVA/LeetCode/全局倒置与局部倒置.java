package Java.LeetCode;

public class 全局倒置与局部倒置 {
    static int lowBit(int x){
        return x&(-x);
    }
    static int query(int[] tree,int x){
        int ans=0;
        for(;x>0;x-=lowBit(x)){
            ans+=tree[x];
        }
        return ans;
    }
    static void update(int[] tree,int x){
        for(;x<tree.length;x+=lowBit(x)){
            tree[x]+=1;
        }
    }
    static int countConversions(int[] nums){
        int[] tree=new int[nums.length+1];
        int ans=0,n=nums.length;
        for(int i=n-1;i>=0;i--){
            ans+=query(tree,nums[i]);
            update(tree,nums[i]+1);
        }
        return ans;
    }
    static boolean isIdealPermutation(int[] nums) {
        int topical=0;
        for(int i=0;i<nums.length-1;i++){
            if(nums[i]>nums[i+1]){
                topical++;
            }
        }
        return topical==countConversions(nums);
    }
    public static void main(String[] args) {
        int ans=countConversions(new int[]{4,3,2,1,0});
        System.out.println(ans);
    }
}
