package JAVA.Luogu;

import java.util.*;
import static java.lang.Integer.*;

public class おとぎの国の高橋君 {
    static int parse(String number,Map<Character,Integer> digit){
        int ans=0;
        for(int i=0,l=number.length();i<l;i++){
            ans=ans*10+digit.get(number.charAt(i));
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        final Map<Character,Integer> digit=new HashMap<>();
        for(int i=0;i<=9;i++){
            char num=sc.next().charAt(0);
            digit.put(num,i);
        }
        int N=sc.nextInt();
        int[][] parsed=new int[N][];
        for(int i=0;i<N;i++){
            String number=sc.next();
            parsed[i]=new int[]{
                parse(number, digit),
                parseInt(number)
            };
        }
        Arrays.sort(parsed,(IA1,IA2)->IA1[0]-IA2[0]);
        for(int[] IA:parsed){
            System.out.println(IA[1]);
        }
        sc.close();
    }
}
