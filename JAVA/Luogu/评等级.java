package Java.Luogu;

import java.util.Scanner;

public class 评等级 {
    private static record
    Student(int id,int study,int quality) {
        int sum(){
            return study+quality;
        }
        int summaryInt(){
            return study*7+quality*3;
        }
        boolean isExcellent(){
            return sum()>140&&summaryInt()>=800;
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        Student[] stu=new Student[N];
        for(int i=0;i<N;i++){
            int id=sc.nextInt();
            int study=sc.nextInt();
            int quality=sc.nextInt();
            stu[i]=new Student(id,study,quality);
        }
        for(int i=0;i<N;i++){
            System.out.println(stu[i].isExcellent()?"Excellent":"Not excellent");
        }
        sc.close();
    }
}
