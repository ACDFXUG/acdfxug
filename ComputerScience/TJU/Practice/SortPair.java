package ComputerScience.TJU.Practice;

import java.util.Arrays;
import java.util.Scanner;



public class SortPair {
    private static class Pair implements Comparable<Pair> {
    final int first, second;
    Pair(int first, int second){
        this.first = first;
        this.second = second;
    }
    public String toString(){
        return first + " " + second;
    }
    public int compareTo(Pair b){
        if(first>b.first){
            return 1;
        }else if(first<b.first){
            return-1;
        }else{
            if(second>b.second){
                return 1;
            }else if(second<b.second){
                return-1;
            }else{
                return 0;
            }
        }
    }
}
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N=sc.nextInt();
        Pair[] pair=new Pair[N];
        for(int i=0;i<N;i++){
            pair[i]=new Pair(sc.nextInt(),sc.nextInt());
        }
        Arrays.sort(pair,Pair::compareTo);
        for(int i=0;i<N;i++){
            System.out.println(pair[i]);
        }
        sc.close();
    }
}
