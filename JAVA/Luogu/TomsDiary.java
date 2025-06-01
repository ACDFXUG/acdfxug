package JAVA.Luogu;

import java.util.Scanner;

public class TomsDiary {
    static boolean isSame(String[] str,int i){
        for(int k=0;k<i;k++){
            if(str[k].equals(str[i])){
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        String[] name=new String[N];
        for(int i=0;i<N;name[i++]=sc.next());
        for(int i=0;i<N;i++){
            System.out.println(isSame(name,i)?"YES":"NO");
        }
        sc.close();
    }
}
