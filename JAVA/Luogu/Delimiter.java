package Java.Luogu;

import java.util.*;

public class Delimiter {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        List<Integer> A=new ArrayList<>();
        for(int x;;){
            x=sc.nextInt();
            if(x==0){
                A.add(0);
                sc.close();
                break;
            }else{
                A.add(x);
            }
        }
        for(int i=A.size()-1;i>=0;i--){
            System.out.println(A.get(i));
        }
    }
}
