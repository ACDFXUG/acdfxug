package JAVA.Luogu;

import java.util.Arrays;

public class 三连击 {
    static int[] BIT(int i){
        int[] bit=new int[3];
        bit[2]=i%10;
        bit[1]=(i-bit[2])%100/10;
        bit[0]=(i-bit[2]-10*bit[1])/100;
        return bit;
    }
    public static void main(String[] args){
        int[] A=new int[9];
        for(int i=0;i<9;i++){
            A[i]=i+1;
        }
        for(int i=100;i<1000&&2*i<1000&&3*i<1000;i++){
            int[] LIST=new int[9];
            for(int j=0;j<9;j++){
                if(j<3){
                    LIST[j]=BIT(i)[j];
                }else if(j>=3&&j<6){
                    LIST[j]=BIT(2*i)[j-3];
                }else if(j>=6){
                    LIST[j]=BIT(3*i)[j-6];
                }
            }
            Arrays.sort(LIST);
            if(Arrays.equals(LIST,A)){
                System.out.printf("%d %d %d\n",i,2*i,3*i);
            }
        }
    }
}
