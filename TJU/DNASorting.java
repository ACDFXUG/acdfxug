package TJU;

import java.util.*;

public class DNASorting {
    private static class DNA implements Comparable<DNA> {
        String dna;
        DNA(String dna){
            this.dna = dna;
        }
        int inversion(String dna){
            int inv=0;
            char[] dnaArray = dna.toCharArray();
            for(int i=0;i<dnaArray.length;i++){
                for(int j=i+1;j<dnaArray.length;j++){
                    if(dnaArray[i]>dnaArray[j]){
                        inv++;
                    }
                }
            }
            return inv;
        }
        // private int inversionOptimized(String dna) {
        //     char[] dnaArray = dna.toCharArray();
        //     int inv = 0;
        //     int[] count = new int[256]; // 记录每个ASCII字符出现次数
        //     // 计算原始字符计数
        //     for (char c : dnaArray) {
        //         count[c]++;
        //     }
        //     // 重新计算倒置数，利用排序后的性质减少比较
        //     for (int i = dnaArray.length - 1; i >= 0; i--) {
        //         for (int j = 0; j < dnaArray[i]; j++) { // 从0到当前字符ASCII值-1，这些字符都小于当前字符
        //             inv += count[j]; // 累加小于当前字符的字符数量，即为倒置对数
        //         }
        //         count[dnaArray[i]]--; // 处理过的字符计数减一
        //     }
        //     return inv;
        // }
        public int compareTo(DNA y){
            return inversion(dna)-inversion(y.dna);
        }
        public String toString(){
            return dna;
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int L=sc.nextInt(),N=sc.nextInt();
        Queue<DNA> dna=new PriorityQueue<>(L);
        for(int i=0;i<N;i++){
            dna.add(new DNA(sc.next()));
        }
        for(int i=0;i<N;i++){
            System.out.println(dna.poll());
        }
        sc.close();
    }
}
