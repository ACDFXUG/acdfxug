package Java.Luogu;

import java.util.*;

public class 選択問題の正答 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt(),M=sc.nextInt();
        Map<Integer,Integer> intCnt=new HashMap<>();
        for(int i=1;i<=N;i++){
            intCnt.merge(sc.nextInt(),1,(a,b)->a+b);
        }
        var sort=intCnt.entrySet().stream()
            .sorted((E1,E2)->E1.getValue()-E2.getValue())
            .map(Map.Entry::getValue).toList();
        if(sort.size()==M){
            int min=sort.get(0),max=sort.get(M-1);
            System.out.println(min+" "+max);
        }else{
            int max=sort.get(sort.size()-1);
            System.out.println("0 "+max);
        }
        sc.close();
    }
}
