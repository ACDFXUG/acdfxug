package JAVA.Luogu;

import java.util.Scanner;

public class 扑克牌MAGIJA {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int r=sc.nextInt(),c=sc.nextInt();
        char[][] POKE=new char[2*r][2*c];
        String[] poke=new String[r];
        for(int i=0;i<r;i++){
            poke[i]=sc.next();
        }
        for(int i=0;i<2*r;i++){
            for(int j=0;j<2*c;j++){
                if(i<r&&j<c){
                    POKE[i][j]=poke[i].toCharArray()[j];
                }else if(i>=r&&j<c){
                    POKE[i][j]=POKE[2*r-i-1][j];
                }else if(i<r&&j>=c){
                    POKE[i][j]=POKE[i][2*c-j-1];
                }else{
                    POKE[i][j]=POKE[2*r-i-1][2*c-j-1];
                }
            }
        }
        int x=sc.nextInt(),y=sc.nextInt();
        POKE[x-1][y-1]=(POKE[x-1][y-1]=='#')?'.':'#';
        for(int i=0;i<2*r;i++){
            for(int j=0;j<2*c;j++){
                System.out.printf(j==2*c-1?"%s\n":"%s",POKE[i][j]);
            }
        }
        sc.close();
    }
}
