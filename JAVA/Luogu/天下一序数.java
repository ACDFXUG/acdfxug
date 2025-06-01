package JAVA.Luogu;

import static java.util.Arrays.sort;
import static java.lang.String.valueOf;

public class 天下一序数 {
    public static void main(String[] args) {
        String[]array=new String[1000];
        for(int i=0;i<array.length;){
            array[i]=valueOf(++i);
        }
        sort(array);
        for(String x:array){
            System.out.println(x);
        }
    }
}
