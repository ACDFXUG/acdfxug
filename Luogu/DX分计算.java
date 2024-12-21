package Luogu;

import java.util.*;

public class DX分计算 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int T=sc.nextInt(),dx;
        for(String s;T-->0;){
            s=sc.next();
            dx=0;
            int[] DX=new int[s.length()+1];
            for(int i=1,l=s.length();i<=l;i++){
                switch(s.charAt(i-1)){
                    case 'P'->{dx+=3;DX[i]=dx;}
                    case 'p'->{dx+=2;DX[i]=dx;}
                    case 'G'->{dx+=1;DX[i]=dx;}
                    case 'g'->DX[i]=dx;
                    case 'm'->DX[i]=dx;
                }
            }
            int q=sc.nextInt();
            for(int l,r;q-->0;){
                l=sc.nextInt();
                r=sc.nextInt();
                System.out.println(DX[r]-DX[l-1]);
            }
        }
        sc.close();
    }
}
