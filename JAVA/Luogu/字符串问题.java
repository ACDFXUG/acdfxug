package JAVA.Luogu;

import java.util.Scanner;

public class 字符串问题 {
    static String insert(String x,String y,int i){
        return y.substring(0, i)+x+y.substring(i);
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),q=sc.nextInt();
        String[] str=new String[n];
        for(int i=0;i<n;i++){
            str[i]=sc.next();
        }
        for(int s=1;s<=q;s++){
            int act=sc.nextInt();
            if(act==1){
                int x=sc.nextInt(),y=sc.nextInt(),i=sc.nextInt();
                str[y-1]=insert(str[x-1],str[y-1],i);
            }else if(act==2){
                int y=sc.nextInt();
                System.out.println(str[y-1]);
            }
        }
        sc.close();
    }
}
