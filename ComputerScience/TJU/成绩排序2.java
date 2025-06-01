package ComputerScience.TJU;

import java.util.Arrays;
import java.util.Scanner;



public class 成绩排序2 {
    private static class StudentGrades implements Comparable<StudentGrades>{
        final String name;
        final int age,score;
        public StudentGrades(String name,int age,int score){
            this.name = name;
            this.age = age;
            this.score = score;
        }
        public int compareTo(StudentGrades student){
            if(score>student.score){
                return 1;
            }else if(score==student.score){
                if(name.compareTo(student.name)>0){
                    return 1;
                }else if(name.compareTo(student.name)==0){
                    if(age>student.age){
                        return 1;
                    }else if(age==student.age){
                        return 0;
                    }else{
                        return -1;
                    }
                }else{
                    return -1;
                }
            }else{
                return -1;
            }
        }
        public String toString(){
            return name+" "+age+" "+score;
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        StudentGrades[] stuGrade=new StudentGrades[N];
        for(int i=0;i<N;i++){
            String name=sc.next();
            int age=sc.nextInt();
            int score=sc.nextInt();
            stuGrade[i]=new StudentGrades(name,age,score);
        }
        Arrays.sort(stuGrade,StudentGrades::compareTo);
        for(StudentGrades grade:stuGrade){
            System.out.println(grade);
        }
        sc.close();
    }
}
