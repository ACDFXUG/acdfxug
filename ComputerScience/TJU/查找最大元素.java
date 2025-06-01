package ComputerScience.TJU;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class 查找最大元素 {
    static char max(char[] a) {
        char max = a[0];
        for (int i = 0; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }
        return max;
    }
    static List<Integer> locateMax(char[] a){
        char max = max(a);
        List<Integer> list = new ArrayList<>();  
        for(int i=0;i<a.length;i++){
            if(a[i]==max){
                list.add(i);
            }
        }
        return list;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        List<StringBuilder> list=new ArrayList<>();
        for(int i=0;i<t;i++){
            String s=sc.next();
            StringBuilder sb=new StringBuilder(s);
            int p=1;
            for(int loc:locateMax(s.toCharArray())){
                sb.insert(loc+p,"(max)");
                p+=5;
            }
            list.add(sb);
        }
        list.forEach(System.out::println);
        sc.close();
    }
}
