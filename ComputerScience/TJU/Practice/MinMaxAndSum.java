package ComputerScience.TJU.Practice;

import java.util.Scanner;

public class MinMaxAndSum {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        long max=-0x7fffffff,min=0x7fffffff,sum=0;
        for(int i=0;i<N;i++){
            int a=sc.nextInt();
            if(a>max) max=a;
            if(a<min) min=a;
            sum+=a;
        }
        System.out.println(min+" "+max+" "+sum);
        sc.close();
    }
}
