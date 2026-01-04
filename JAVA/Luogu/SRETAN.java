package Java.Luogu;

import java.util.*;

public class SRETAN {
    static int getM(int k){
        for(int m=1;m<=31;m++){
            if(k>((1<<m)-2)&&k<=((1<<(m+1))-2)){
                return m;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int k=sc.nextInt();
        int m=getM(k);
        int delta=k-(1<<m)+1;
        String ans=String.format(
            "%"+m+"s",
            Integer.toBinaryString(delta)
        ).replaceAll("\\s","4")
        .replaceAll("0","4")
        .replaceAll("1","7");
        System.out.println(ans);
        sc.close();
    }
}
