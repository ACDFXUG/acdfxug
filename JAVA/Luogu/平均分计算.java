package JAVA.Luogu;

import java.util.*;

public class 平均分计算 {
    static int gcd(int a,int b){
        return b==0?a:gcd(b,a%b);
    }
    private static class Course{
        TreeSet<Integer> ids;
        HashMap<Integer,Integer> participation;
        HashMap<Integer,Integer> score;
        Course(int pi){
            this.ids=new TreeSet<>();
            this.participation=new HashMap<>(pi);
            this.score=new HashMap<>(pi);
        }
        int getScore(int ID){
            if(!participation.containsKey(ID)) return 0;
            var sc=participation.values().stream()
                .sorted(Comparator.reverseOrder())
                .toArray(Integer[]::new);
            int rank=1;
            HashMap<Integer,Integer> ranking=new HashMap<>();
            for(var I:sc){
                ranking.putIfAbsent(I,rank);
                ++rank;
            }
            int r=ranking.get(participation.get(ID));
            return score.get(r);
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        final int n=sc.nextInt();
        Course[] courses=new Course[n];
        for(int pi,i=0;i<n;++i){
            pi=sc.nextInt();
            courses[i]=new Course(pi);
            for(int j=0;j<pi;++j){
                courses[i].ids.add(sc.nextInt());
            }
            for(var id:courses[i].ids){
                courses[i].participation.put(id,sc.nextInt());
            }
            for(int j=1;j<=pi;++j){
                courses[i].score.put(j,sc.nextInt());
            }
        }
        int ID=sc.nextInt(),courseCnt=0,allScore=0;
        for(var cos:courses){
            int score=cos.getScore(ID);
            if(score>0){
                ++courseCnt;
                allScore+=score;
            }
        }
        if(allScore%courseCnt==0){
            System.out.println(allScore/courseCnt);
            sc.close();
            return;
        }
        int gcd=gcd(courseCnt,allScore);
        courseCnt/=gcd;
        allScore/=gcd;
        int a=allScore/courseCnt;
        if(a==0){
            System.out.println(allScore+"/"+courseCnt);
        }else{
            System.out.println(a+"+"+allScore%courseCnt+"/"+courseCnt);
        }
        sc.close();
    }
}
