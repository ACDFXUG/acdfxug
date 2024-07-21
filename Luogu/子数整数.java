package Luogu;

import java.util.Scanner;

public class 子数整数 {
    static int[] sub_num(String _NUM){
        int[] ans=new int[3];
        for(int i=0;i<3;i++){
            ans[i]=Integer.parseInt(_NUM.substring(i, i+3));
        }
        return ans;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int k=sc.nextInt(),t=0;
        for(int i=10000;i<=30000;i++){
            int[] sub=sub_num(i+"");
            if(sub[0]%k!=0){
                continue;
            }else{
                if(sub[1]%k!=0){
                    continue;
                }else{
                    if(sub[2]%k!=0){
                        continue;
                    }else{
                        System.out.println(i);
                        t++;
                    }
                }
            }
        }
        if(t==0){
            System.out.println("No");
        }
        sc.close();
    }
}
