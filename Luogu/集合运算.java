package Luogu;

import java.util.Arrays;
import java.util.Scanner;

public class 集合运算 {
    static int[] SET_ADD(int[] A,int[] B){
        int[] add=new int[A.length+B.length];
        for(int i=0;i<add.length;i++){
            add[i]=i<A.length?A[i]:B[i-A.length];
        }
        Arrays.sort(add);
        return add;
    }
    static int[] DELETE(int[] A,int B){
        int[] del=new int[A.length-1];int i;
        for(i=0;i<A.length;i++){
            if(A[i]==B){
                break;
            }
        }
        for(int k=0;k<del.length;k++){
            del[k]=A[k+(k<i?0:1)];
        }
        Arrays.sort(del);
        return del;
    }
    static int[] SET_DELETE(int[] A,int[] B){  //A-B
        for(int i=0;i<B.length;i++){
            A=DELETE(A, B[i]).clone();
        }
        Arrays.sort(A);
        int[] DELETE=A.clone();
        return DELETE;
    }
    static int[] SET_AND(int[] A,int[] B){
        int[] wiu=new int[64];
        int t=0;
        for(int x:A){
            for(int y:B){
                if(x==y){
                    wiu[t]=x;
                    t++;
                }
            }
        }
        int[] and=new int[t];
        for(int i=0;i<t;i++){
            and[i]=wiu[i];
        }
        return and;
    }
    static int[] SET_OR(int[] A,int[] B){
        int[] or=new int[A.length+B.length-SET_AND(A, B).length];
        or=SET_ADD(SET_DELETE(A, SET_AND(A, B)), B);
        return or;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int x=sc.nextInt();
        int[] A=new int[x];
        for(int i=0;i<x;i++){
            A[i]=sc.nextInt();
        }
        int y=sc.nextInt();
        int[] B=new int[y];
        for(int i=0;i<y;i++){
            B[i]=sc.nextInt();
        }
        System.out.println(x);
        for(int p:SET_AND(A, B)){
            System.out.printf("%d ",p);
        }
        System.out.println();
        for(int p:SET_OR(A, B)){
            System.out.printf("%d ",p);
        }
        System.out.println();
        sc.close();
    }
}
