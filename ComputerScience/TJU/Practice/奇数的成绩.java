package ComputerScience.TJU.Practice;

import java.util.Scanner;

public class 奇数的成绩 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int T=sc.nextInt();
        for(int i=0;i++<T;){
            int n=sc.nextInt(),ans=1;
            for(int j=0;j++<n;){
                int a=sc.nextInt();
                if((a&1)==1){
                    ans*=a;
                }
            }
            System.out.println(ans);
        }
        sc.close();
    }
}
