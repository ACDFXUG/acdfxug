package JAVA.Luogu;

import java.util.Scanner;

public class UniClass {
    static int MAX(int[] a){
        int max=0x80000000;
        for(int x : a){
            max=x>max?x:max;
        }
        return max;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        String[] CLASS=new String[n];
        int[] max=new int[7];
        for(int i=0;i<n;CLASS[i++]=sc.next());
        for(int i=0;i<7;i++){
            for(int j=0;j<n;j++){
                max[i]+=CLASS[j].toCharArray()[i]-'0';
            }
        }
        System.out.println(MAX(max));
        sc.close();
    }
}
