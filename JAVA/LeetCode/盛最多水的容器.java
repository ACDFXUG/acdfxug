package Java.LeetCode;

public class 盛最多水的容器 {
    static int maxArea(int[] height){
        int max=0;
        int i=0,j=height.length-1;
        while(i<j){
            int area=(j-i)*Math.min(height[i],height[j]);
            max=Math.max(max,area);
            if(height[i]<height[j]) i++;
            else j--;
        }
        return max;
    }
    public static void main(String[] args) {
        int[] a={1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(a));
    }
}
