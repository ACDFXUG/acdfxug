package Java.Luogu;

import java.util.*;

public class 梦熊培养计划 {
    private static class Student
    implements Comparable<Student>{
        private String name;
        private int[] score;
        Student(String name,int... score){
            this.name=name;
            this.score=score;
        }
        double getAverage(){
            double avg=.0;
            for(int i:score){
                avg+=i;
            }
            return avg/score.length;
        }
        double getStdDeviation(){
            double sigma=.0,avg=getAverage();
            for(int i:score){
                sigma+=(i-avg)*(i-avg);
            }
            return Math.sqrt(sigma/score.length);
        }
        public int compareTo(Student s){
            int ans=Double
            .compare(s.getStdDeviation(),getStdDeviation());
            return ans==0?name.compareTo(s.name):ans;
        }
        public String toString(){
            return name;
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),m=sc.nextInt();
        Student[] students=new Student[n];
        for(int i=0;i<n;i++){
            String name=sc.next();
            int[] tmp=new int[m];
            for(int j=0;j<m;j++){
                tmp[j]=sc.nextInt();
            }
            students[i]=new Student(name,tmp);
        }
        Arrays.sort(students);
        for(int i=0;i<(n<20?n:20);i++){
            System.out.println(students[i]);
        }
        sc.close();
    }
}
