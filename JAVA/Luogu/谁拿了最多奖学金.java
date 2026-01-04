package Java.Luogu;

import java.util.*;

public class 谁拿了最多奖学金 {
    private static class ScholarShip{
        String name;
        int average,classScore;
        char isLeader,isWestern;
        int articles;
        int scholarship;
        ScholarShip(String name,int average,int classScore,char isLeader,char isWestern,int articles){
            this.name = name;
            this.average = average;
            this.classScore = classScore;
            this.isLeader = isLeader;
            this.isWestern = isWestern;
            this.articles = articles;
            this.scholarship = 0;
        }
        boolean isFellowShip(){//8000
            return average>80&&articles>=1;
        }
        boolean is54Ship(){//4000
            return average>85&&classScore>80;
        }
        boolean isHighScore(){//2000
            return average>90;
        }
        boolean isWestern(){//1000
            return average>85&&isWestern=='Y';
        }
        boolean isLeader(){//850
            return classScore>80&&isLeader=='Y';
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        Map<Integer,ScholarShip> ships=new HashMap<>();
        for(int i=0;i<N;i++){
            String name=sc.next();
            int avg=sc.nextInt();
            int classScore=sc.nextInt();
            char isLeader=sc.next().charAt(0);
            char isWestern=sc.next().charAt(0);
            int articles=sc.nextInt();
            ships.put(i,new ScholarShip(name, avg, classScore, isLeader, isWestern, articles));
        }
        for(int i=0;i<N;i++){
            ScholarShip ss=ships.get(i);
            if(ss.isFellowShip()){
                ss.scholarship+=8000;
            }
            if(ss.is54Ship()){
                ss.scholarship+=4000;
            }
            if(ss.isHighScore()){
                ss.scholarship+=2000;
            }
            if(ss.isWestern()){
                ss.scholarship+=1000;
            }
            if(ss.isLeader()){
                ss.scholarship+=850;
            }
        }
        List<ScholarShip> SS=ships.entrySet().stream()
        .sorted((SS1,SS2)->{
            if(SS1.getValue().scholarship!=SS2.getValue().scholarship){
                return SS2.getValue().scholarship-SS1.getValue().scholarship;
            }else{
                return SS1.getKey()-SS2.getKey();
            }
        }).map(E->E.getValue()).toList();
        int ans=0;
        for(ScholarShip ss:SS){
            ans+=ss.scholarship;
        }
        System.out.printf("%s\n%d\n%d\n",SS.get(0).name,SS.get(0).scholarship,ans);
        sc.close();
    }
}
