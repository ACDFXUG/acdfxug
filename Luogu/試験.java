package Luogu;

import java.util.*;
import static java.util.Arrays.sort;

public class 試験 {
    private static class Student
    implements Comparable<Student> {
        final int ID;
        int M,E;
        Student(int ID,int M,int E){
            this.ID=ID;
            this.M=M;
            this.E=E;
        }
        public int compareTo(Student s){
            if(M+E!=s.M+s.E){
                return s.E+s.M-(E+M);
            }else if(M!=s.M){
                return s.M-M;
            }else{
                return ID-s.ID;
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        Student[] students=new Student[N];
        for(int i=0;i<N;){
            students[i]=new Student(++i,sc.nextInt(),0);
        }
        for(int i=0;i<N;i++){
            students[i].E=sc.nextInt();
        }
        sort(students);
        for(Student stu:students){
            System.out.print(stu.ID+" ");
        }
        System.out.println();
        sc.close();
    }
}
