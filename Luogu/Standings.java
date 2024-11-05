package Luogu;

import java.util.*;

public class Standings {
    private static record Person(int index,double ai,double bi)
    implements Comparable<Person>{
        public int compareTo(Person p){
            double rate1=ai/(bi+ai);
            double rate2=p.ai/(p.bi+p.ai);
            return rate1==rate2?index-p.index:Double.compare(rate2,rate1);
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        Person[] ps=new Person[n];
        for(int i=0;i<n;i++){
            double a=sc.nextDouble(),
                b=sc.nextDouble();
            ps[i]=new Person(i+1,a,b);
        }
        Arrays.sort(ps);
        for(Person p:ps)
            System.out.println(p.index);
        sc.close();
    }
}
