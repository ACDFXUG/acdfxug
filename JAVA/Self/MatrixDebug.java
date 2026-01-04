package Java.Self;

import java.util.Scanner;

public class MatrixDebug {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        Matrix P=new Matrix(new double[][]{
            {-1,0,0},
            {3,2,0},
            {0,0,-1}
        });
        System.out.println(P.power(10));
        sc.close();
    }
}
