package Luogu;

import java.util.*;

public class 东风谷早苗 {
    private static class Point{
        int X,Y;
        Point(int X,int Y){
            this.X=X;
            this.Y=Y;
        }
        public String toString(){
            return X+" "+Y;
        }
        Point addEqual(Point p){
            X+=p.X;
            Y+=p.Y;
            return this;
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String action=sc.next();
        int T=sc.nextInt(),L=action.length();
        if(T<=L){
            Point goddamn=new Point(0,0);
            for(int i=0;i<T;i++){
                switch(action.charAt(i)){
                    case 'N'->goddamn.Y++;
                    case 'S'->goddamn.Y--;
                    case 'E'->goddamn.X++;
                    case 'W'->goddamn.X--;
                }
            }
            System.out.println(goddamn);
        }else{
            int loop=T/L;
            Point goddamn=new Point(0,0),
            remain=new Point(0,0),
            ans=new Point(0,0);
            for(int i=0;i<L;i++){
                switch(action.charAt(i)){
                    case 'N'->goddamn.Y++;
                    case 'S'->goddamn.Y--;
                    case 'E'->goddamn.X++;
                    case 'W'->goddamn.X--;
                }
            }
            for(int i=0;i<T%L;i++){
                switch(action.charAt(i)){
                    case 'N'->remain.Y++;
                    case 'S'->remain.Y--;
                    case 'E'->remain.X++;
                    case 'W'->remain.X--;
                }
            }
            ans.X=goddamn.X*loop;
            ans.Y=goddamn.Y*loop;
            System.out.println(ans.addEqual(remain));
        }
        sc.close();
    }
}
