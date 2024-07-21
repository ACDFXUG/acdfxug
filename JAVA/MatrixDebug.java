package JAVA;

import java.util.Scanner;

public class MatrixDebug {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int R=sc.nextInt(),C=sc.nextInt();
        GenericMatrix<Integer> a=new GenericMatrix<>(R, C);
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                a.inputAt(i, j, sc.nextInt());
            }
        }
        System.out.println(a.add(a));
        sc.close();
    }
}
