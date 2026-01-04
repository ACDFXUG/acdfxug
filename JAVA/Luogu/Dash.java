package Java.Luogu;

import java.util.*;

public class Dash {
    private static class Point{
        int x,y;
        Point(int x,int y){
            this.x=x;
            this.y=y;
        }
        public int hashCode(){
            return Objects.hash(x,y);
        }
        public boolean equals(Object p){
            if(this==p){
                return true;
            }
            if(p==null){
                return false;
            }
            return p instanceof Point point
                &&x==point.x&&y==point.y;
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt(),M=sc.nextInt();
        int H=sc.nextInt(),K=sc.nextInt();
        String S=sc.next();
        var medic=new HashSet<Point>(M){{
            for(int i=0;i<M;i++){
                int x=sc.nextInt(),
                    y=sc.nextInt();
                add(new Point(x,y));
            }
        }};
        sc.close();
        Point start=new Point(0,0);
        for(int i=0;i<N;i++){
            char act=S.charAt(i);
            switch(act){
                case 'R'->start.x++;
                case 'L'->start.x--;
                case 'U'->start.y++;
                case 'D'->start.y--;
            }
            H--;
            if(H<0){
                System.out.println("No");
                return;
            }else{
                if(medic.contains(start)){
                    if(H<K){
                        H=K;
                        medic.remove(start);
                    }
                }
            }
        }
        System.out.println("Yes");
    }
}
