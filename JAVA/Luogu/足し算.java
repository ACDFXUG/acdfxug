package Java.Luogu;

import java.util.*;

public class 足し算 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),m=0;
        String bits=Integer.toBinaryString(n);
        List<Integer> arr=new ArrayList<>(n);
        for(int l=bits.length()-1,i=l;i>=0;i--){
            if(bits.charAt(i)=='1'){
                arr.add(1<<(l-i));
                m++;
            }
        }
        System.out.println(m);
        arr.forEach(System.out::println);
        sc.close();
    }
}
