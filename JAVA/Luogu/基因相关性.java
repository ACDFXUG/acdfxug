package JAVA.Luogu;

import java.util.Scanner;

public class 基因相关性 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        double simirate=sc.nextDouble(),similar=0;
        char[] gene1=sc.next().toCharArray(),gene2=sc.next().toCharArray();
        int L=gene1.length;
        for(int i=0;i<L;i++){
            if(gene1[i]==gene2[i]){
                similar++;
            }
        }
        System.out.println(similar/L>=simirate?"yes":"no");
        sc.close();
    }
}
