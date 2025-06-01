package ComputerScience.TJU;

import java.util.Arrays;
import java.util.Scanner;


public class TJU成绩查询 {
    private static class GradeTJU{
    String name;
    double score1,
        score2,
        score3,
        score4,
        score5;
    double[] point;
    public GradeTJU(String name,int score1,int score2,int score3,int score4,int score5,double[] point){
        this.name = name;
        this.score1 = score1;
        this.score2 = score2;
        this.score3 = score3;
        this.score4 = score4;
        this.score5 = score5;
        this.point = point;
    }
    // public double getWeight(int point1,int point2,int point3,int point4,int point5){
    //     return (point1*score1+point2*score2+point3*
    //     score3+point4*score4+point5*score5)
    //     /(point1+point2+point3+point4+point5);
    // }

    public double getWeight(){
        return (point[0]*score1+point[1]*score2+point[2]*score3+point[3]*score4+point[4]*score5)
        /(point[0]+point[1]+point[2]+point[3]+point[4]);
    }
    public static int compare(GradeTJU a,GradeTJU b){
        if(a.getWeight()>b.getWeight()){
            return -1;
        }
        else if(a.getWeight()<b.getWeight()){
            return 1;
        }
        else{
            return 0;
        }
    }
    public void println(){
        System.out.printf("%s %.2f\n",name,getWeight());
    }
}

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        double[] point=new double[5];
        for(int i=0;i<5;point[i++]=sc.nextInt());
        int N=sc.nextInt();
        GradeTJU[] grade=new GradeTJU[N];
        for(int i=0;i<N;grade[i++]=new GradeTJU(sc.next(),sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.nextInt(),point));
        Arrays.sort(grade,GradeTJU::compare);
        for(int i=0;i<N;grade[i++].println());
        sc.close();
    }
}
