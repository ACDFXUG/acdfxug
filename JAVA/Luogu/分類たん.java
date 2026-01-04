package Java.Luogu;

import java.util.*;

public class 分類たん {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        String[] ss=s.split("\\s+");
        StringJoiner ans=new StringJoiner(",");
        for(String part:ss){
            ans.add(part);
        }
        System.out.println(ans);
        sc.close();
    }
}
