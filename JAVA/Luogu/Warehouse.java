package Java.Luogu;

import java.util.*;

public class Warehouse {
    static class Pair{
        int x,y;
        Pair(int x,int y){
            this.x=x;
            this.y=y;
        }
        public String toString(){
            return x+" "+y;
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),m=sc.nextInt(),
            k=sc.nextInt();
        boolean[] loc=new boolean[m*n];
        var warehouse=new HashMap<String,Pair>();
        while(k-->0){
            int act=sc.nextInt();
            if(act==1){
                int x=sc.nextInt(),
                    y=sc.nextInt(),
                    idx=m*(x-1)+(y-1);
                String id=sc.next();
                if(loc[idx]){
                    for(int i=idx;i<m*n;i++){
                        if(!loc[i]){
                            loc[i]=true;
                            warehouse.put(id,new Pair(i/m+1,i%m+1));
                            break;
                        }
                    }
                }else{
                    loc[idx]=true;
                    warehouse.put(id,new Pair(x,y));
                }
            }else if(act==-1){
                String id=sc.next();
                if(warehouse.containsKey(id)){
                    Pair p=warehouse.get(id);
                    System.out.println(p);
                    loc[m*(p.x-1)+(p.y-1)]=false;
                    warehouse.remove(id);
                }else{
                    System.out.println("-1,-1");
                }
            }
        }
        sc.close();
    }
}
