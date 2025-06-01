package JAVA.LeetCode;

import java.util.*;
import static java.lang.Math.*;

public class 找到K个最接近的元素 {
    static List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> closest=new ArrayList<Integer>(){{
            for(int i:arr){
                add(i);
            }
        }};
        closest.sort((I1,I2)->{
            int d1=abs(I1-x);
            int d2=abs(I2-x);
            return d1==d2?I1-I2:d1-d2;
        });
        return new ArrayList<Integer>(closest.subList(0,k)){{
            sort(null);
        }};
    }
    public static void main(String[] args) {
        int[] arr={1,2,3,4,5};
        System.out.println(findClosestElements(arr,4,3));
    }
}
