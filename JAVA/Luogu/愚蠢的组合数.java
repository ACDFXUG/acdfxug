package Java.Luogu;

import java.util.Scanner;

public class 愚蠢的组合数 {
    static byte isOddCombination(int N,int K){
        String binN=Integer.toBinaryString(N);
        String binK=Integer.toBinaryString(K);
        binK="0".repeat(binN.length()-binK.length())+binK;
        for(int i=0;i<binN.length();i++){
            if(binK.charAt(i)>binN.charAt(i)){
                return 0;
            }
        }
        return 1;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int T=sc.nextInt();
        assert T>=1;
        while(T-->0){
            int N=sc.nextInt(),K=sc.nextInt();
            System.out.println(isOddCombination(N, K));
        }
        sc.close();
    }
}
