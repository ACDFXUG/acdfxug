package JAVA.Luogu;

import java.util.Scanner;

public class CycleHit {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String[] pencil=new String[4];
        for(int i=0;i<4;pencil[i++]=sc.next());
        int H=0,B2=0,B3=0,HR=0;
        for(String x:pencil){
            switch(x){
                case "H"->H++;
                case "2B"->B2++;
                case "3B"->B3++;
                case "HR"->HR++;
            }
        }
        if(H==1&&B2==1&&B3==1&&HR==1){
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }
        sc.close();
    }
}
