package Java.Luogu;

import java.math.BigInteger;
import java.util.Scanner;

public class 晚饭 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        BigInteger x=new BigInteger(sc.next());
        String menu=sc.next();
        if(menu.contains("B")&&!menu.contains("C")){
            System.out.println(x.multiply(new BigInteger("8")).divide(new BigInteger("10")));
        }else if(menu.contains("C")&&!menu.contains("B")){
            System.out.println(x.multiply(new BigInteger("7")).divide(new BigInteger("10")));
        }else if(menu.contains("B")&&menu.contains("C")){
            System.out.println(x.multiply(new BigInteger("6")).divide(new BigInteger("10")));
        }else if(!menu.contains("B")&&!menu.contains("C")){
            System.out.println(x);   
        }
        sc.close();
    }
}
