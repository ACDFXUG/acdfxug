package JAVA.Luogu;

import java.util.*;
import java.math.*;

public class 高精求小数幂 {
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner sc=new Scanner(System.in);
        for(;sc.hasNextDouble();){
            BigDecimal a=new BigDecimal(sc.next());
            int exp=sc.nextInt();  //>0
            BigDecimal ans=a.pow(exp);
            System.out.println(ans.toPlainString()
                .replaceAll("0+$","")
                .replaceAll("\\.$","")
                .replaceAll("^0+",""));
        }
    }
}
