package JAVA.Luogu;

import java.util.*;
import static java.lang.Math.*;

public class 旗鼓相当的对手 {
    private static class Student{
        String name;
        int Chin,Math,Engl;
        Student(String name,int Chin,int Math,int Engl){
            this.name=name;
            this.Chin=Chin;
            this.Math=Math;
            this.Engl=Engl;
        }
        int sum(){
            return Chin+Math+Engl;
        }
        boolean isCloseTo(Student stu){
            return abs(Chin-stu.Chin)<=5&&
            abs(Math-stu.Math)<=5&&
            abs(Engl-stu.Engl)<=5&&
            abs(sum()-stu.sum())<=10;
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        Student[] stus=new Student[N];
        for(int i=0;i<N;i++){
            stus[i]=new Student(sc.next(),sc.nextInt(),sc.nextInt(),sc.nextInt());
        }
        for(int i=0;i<N;i++){
            for(int j=i+1;j<N;j++){
                if(stus[i].isCloseTo(stus[j])){
                    if(stus[i].name.compareTo(stus[j].name)<0){
                        System.out.println(stus[i].name+" "+stus[j].name);
                    }else{
                        System.out.println(stus[j].name+" "+stus[i].name);
                    }
                }
            }
        }
        sc.close();
    }
}
