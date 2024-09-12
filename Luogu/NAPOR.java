package Luogu;

import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class NAPOR {
    static final Pattern NUMBER=Pattern.compile("\\d+");
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        List<BigInteger> nums=new ArrayList<>();
        for(int i=0;i<N;i++){
            String str=sc.next();
            for(Matcher num=NUMBER.matcher(str);num.find();){
                nums.add(new BigInteger(num.group()));
            }
        }
        nums.sort(null);
        nums.forEach(System.out::println);
        sc.close();
    }
}
