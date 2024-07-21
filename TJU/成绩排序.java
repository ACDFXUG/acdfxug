package TJU;

import java.util.Arrays;
import java.util.Scanner;



public class 成绩排序 {
    static class Grade implements Comparable<Grade>{
        int chinese,math;
        public Grade(int chinese,int math){
            this.chinese=chinese;
            this.math=math;
        }
        public int compareTo(Grade b){
            if(chinese>b.chinese){
                return 1;
            }else if(chinese==b.chinese){
                if(math>b.math){
                    return 1;
                }else if(math==b.math){
                    return 0;
                }else{
                    return -1;
                }
            }else{
                return -1;
            }
        }
        public String toString(){
            return chinese+" "+math;
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        Grade[] g=new Grade[n];
        for(int i=0;i<n;i++){
            g[i]=new Grade(sc.nextInt(),sc.nextInt());
        }
        Arrays.sort(g,Grade::compareTo);
        for(Grade x:g){
            System.out.println(x);
        }
        sc.close();
    }
}
