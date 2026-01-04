package Java.Luogu;

import java.util.Scanner;

public class YoungPhysicist {
    static boolean isForceBalanced(int[][] force,int N){
        int x=0,y=0,z=0;
        for(int i=0;i<N;i++){
            x+=force[i][0];
            y+=force[i][1];
            z+=force[i][2];
        }
        return x==0&&y==0&&z==0;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt(),force[][]=new int[N][3];
        for(int i=0;i<N;i++){
            for(int j=0;j<3;j++){
                force[i][j]=sc.nextInt();
            }
        }
        System.out.println(isForceBalanced(force, N)?"YES":"NO");
        sc.close();
    }
}
