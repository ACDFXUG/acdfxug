package Java.Luogu;

import java.util.*;
import java.math.*;

public class BallotAnalyzingDevice {
    private static record Pair<L,R>(L left,R right){}
    private static class Campaigher
    implements Comparable<Campaigher>{
        final String name;
        int vote_cnt;
        Campaigher(String name){
            this.name=name;
            this.vote_cnt=0;
        }
        public int compareTo(Campaigher cam){
            return cam.vote_cnt-vote_cnt;
        }
    }
    static Pair<Boolean,Integer> isValid(String vote){
        int cnt=0,lct=0;
        for(int i=0,l=vote.length();
        i<l;i++){
            if(vote.charAt(i)=='X'){
                cnt++;
                lct=i;
            }
        }
        return new Pair<>(cnt==1,lct);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),m=sc.nextInt(),
        invalid=0;
        Campaigher[] cam=new Campaigher[n];
        for(int i=0;i<n;i++){
            cam[i]=new Campaigher(sc.next());
        }
        for(int i=0;i<m;i++){
            Pair<Boolean,Integer> p=
            isValid(sc.next());
            if(p.left){
                cam[p.right].vote_cnt+=100;
            }else{
                invalid+=100;
            }
        }
        Arrays.sort(cam);
        for(Campaigher c:cam){
            BigDecimal tmp=new BigDecimal(c.vote_cnt)
            .divide(new BigDecimal(m),2,RoundingMode.HALF_UP);
            System.out.printf("%s %s%%\n",c.name,tmp);
        }
        System.out.printf(
            "Invalid %s%%\n",
            new BigDecimal(invalid)
            .divide(new BigDecimal(m),2,RoundingMode.HALF_UP)
        );
        sc.close();
    }
}
