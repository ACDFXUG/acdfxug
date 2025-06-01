package JAVA.Luogu;

import java.util.*;

public class OptimalRecomm {
    private static
    record Company(int ai,int bi,int ci,int wi)
    implements Comparable<Company>{
        public int compareTo(Company cpn){
            return cpn.wi-wi;
        }
    }
    private static record
    Employee(int xi,int yi,int zi){
        public boolean isFit(Company cpn){
            return xi>=cpn.ai&&yi>=cpn.bi&&zi>=cpn.ci;
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt(),M=sc.nextInt();
        Company[] cpns=new Company[N];
        for(int i=0;i<N;i++){
            int ai=sc.nextInt(),
                bi=sc.nextInt(),
                ci=sc.nextInt(),
                wi=sc.nextInt();
            cpns[i]=new Company(ai,bi,ci,wi);
        }
        Employee[] emps=new Employee[M];
        for(int i=0;i<M;i++){
            int xi=sc.nextInt(),
                yi=sc.nextInt(),
                zi=sc.nextInt();
            emps[i]=new Employee(xi,yi,zi);
        }
        Arrays.sort(cpns);
        for(Employee emp:emps){
            int max=0;
            for(Company cpn:cpns){
                if(emp.isFit(cpn)){
                    max=cpn.wi>max?cpn.wi:max;
                    break;
                }
            }
            System.out.println(max);
        }
        sc.close();
    }
}
