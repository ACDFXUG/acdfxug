package Java.Luogu;

import java.util.Scanner;
import java.math.BigInteger;

public class 麦森数 {
    static String addzero(String _M){
        String M=_M;
        for(int i=0;i<500-_M.length();i++){
            M="0"+M;
        }
        return M;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int P=sc.nextInt();
        BigInteger misen=(new BigInteger("1").shiftLeft(P)).subtract(new BigInteger("1"));
        String M=misen.toString();
        int L=M.length();
        System.out.println(L);
        if(L<500){
            M=addzero(M);
            for(int i=0;i<500;i++){
                System.out.printf(i%50==49?"%c\n":"%c",M.charAt(i));
            }
        }else{
            for(int i=L-500;i<L;i++){
                System.out.printf((i-L+500)%50==49?"%c\n":"%c",M.charAt(i));
            }
        }
        sc.close();
    }
}
