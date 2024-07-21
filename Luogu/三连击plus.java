package Luogu;

import java.util.Arrays;
import java.util.Scanner;

public class 三连击plus {
    static int[] BIT(int i){
        int[] bit=new int[3];
        bit[2]=i%10;
        bit[1]=(i-bit[2])%100/10;
        bit[0]=(i-bit[2]-10*bit[1])/100;
        return bit;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int[] A={1,2,3,4,5,6,7,8,9};
        int a=sc.nextInt(),b=sc.nextInt(),c=sc.nextInt(),t=0;
        for(int i=100;a*i<1000&&b*i<1000&&c*i<1000;i++){
            int[] LIST=new int[9];
            for(int j=0;j<9;j++){
                if(j<3){
                    LIST[j]=BIT(a*i)[j];
                }else if(j>=3&&j<6){
                    LIST[j]=BIT(b*i)[j-3];
                }else if(j>=6){
                    LIST[j]=BIT(c*i)[j-6];
                }
            }
            Arrays.sort(LIST);
            if(Arrays.equals(LIST,A)){
                t++;
                System.out.printf("%d %d %d\n",a*i,b*i,c*i);
            }   
        }
        if(t==0){
            System.out.println("No!!!");
        }
        sc.close();
    }
}
