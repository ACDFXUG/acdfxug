package JAVA.LeetCode;

import java.util.Scanner;

public class 整数转罗马数字 {
    static String intToRoman(int num){ //1<=num<=3999
        String[] roman={"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        int[] nums={1000,900,500,400,100,90,50,40,10,9,5,4,1};
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<13;i++){
            while(num>=nums[i]){
                num-=nums[i];
                sb.append(roman[i]);
            }
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println(intToRoman(sc.nextInt()));
        sc.close();
    }
}
