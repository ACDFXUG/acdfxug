package ComputerScience.TJU;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 帖子管理 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        @SuppressWarnings("unchecked")
        ArrayList<Integer> post[]=new ArrayList[100001];
        List<Integer> ans=new ArrayList<>();
        for(int i=0;i<N;i++){
            String s=sc.next();
            int x=sc.nextInt(),y=sc.nextInt();
            if(s.equals("ADD")){
                if(post[x]==null){
                    post[x]=new ArrayList<>();
                }
                post[x].add(y);
            }else if(s.equals("QUERY")){
                if(post[x]==null||post[x].size()<y){
                    ans.add(-1);
                }else{
                    ans.add(post[x].get(y-1));
                }
            }
        }
        ans.forEach(System.out::println);
        sc.close();
    }
    
}
