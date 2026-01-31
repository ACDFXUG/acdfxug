package ComputerScience.TJU.YJS;

import java.util.*;

public class 消消乐 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int T=sc.nextInt();
        for(String str;T-->0;){
            str=sc.next();
            System.out.println(str.replaceAll("t{2,}",""));
        }
        sc.close();
    }
}
