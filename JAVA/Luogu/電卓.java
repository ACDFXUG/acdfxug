package Java.Luogu;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class 電卓 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        BigDecimal A=sc.nextBigDecimal();
        BigDecimal B=sc.nextBigDecimal();
        if(!B.equals(BigDecimal.ZERO)){
            String divide=A.divide(B,3,RoundingMode.HALF_UP).toString();
            System.out.println(divide.substring(0,divide.indexOf(".")+3));
        }else{
            System.out.println("ERROR");
        }
        sc.close();
    }
}
