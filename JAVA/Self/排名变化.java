package Java.Self;

import java.util.*;

public class 排名变化 {
    static Map<Student,Integer> mRanking=new HashMap<>();
    static Map<Student,Integer> eRanking=new HashMap<>();
    static class Student implements Comparable<Student>{
        int a,b;
        Student(int a,int b){
            this.a=a;
            this.b=b;
        }
        public int compareTo(Student stu) {
            int delta1=eRanking.get(this)-mRanking.get(this);
            int delta2=eRanking.get(stu)-mRanking.get(stu);
            if(delta1!=delta2) return delta1-delta2;
            return stu.a-a;
        }

        public int hashCode(){
            return a*31+b;
        }
        public boolean equals(Object o){
            if(this==o) return true;
            if(o==null) return false;
            return o instanceof Student stu
                &&stu.a==a
                &&stu.b==b;
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        Student[] students=new Student[N];
        for(int i=0;i<N;++i){
            students[i]=new Student(0,0);
            students[i].a=sc.nextInt();
        }
        for(int i=0;i<N;++i){
            students[i].b=sc.nextInt();
        }
        for(int i=0;i<N;++i){
            mRanking.put(students[i],i+1);
            eRanking.put(students[i],i+1);
        }
        var list=Arrays.stream(students)
                .sorted()
                .toList();
        Map<Student,Integer> ranking=new HashMap<>();
        for(int i=0;i<N;++i){
            ranking.put(list.get(i),i+1);
        }
        for(int i=0;i<N;++i){
            System.out.printf(i==N-1?"%d\n":"%d ",ranking.get(students[i]));
        }
        sc.close();
    }
}
