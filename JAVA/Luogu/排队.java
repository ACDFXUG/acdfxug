package JAVA.Luogu;

import java.util.*;

public class 排队 {
     public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int T=sc.nextInt();
        while(T-->0){
            int n=sc.nextInt();
            byte[] gender=new byte[n];
            for(int i=0;i<n;i++){
                gender[i]=sc.nextByte();
            }
            String[] height=new String[n];
            for(int i=0;i<n;i++){
                height[i]=sc.next();
            }
            List<String> boy=new ArrayList<>();
            List<String> girl=new ArrayList<>();
            for(int i=0;i<n;i++){
                if(gender[i]==1){
                    boy.add(height[i]);
                }else{
                    girl.add(height[i]);
                }
            }
            boy.sort((strH1,strH2)->{
                double h1=Double.parseDouble(strH1);
                double h2=Double.parseDouble(strH2);
                if(h1>h2){
                    return 1;
                }else if(h1<h2){
                    return-1;
                }else{
                    return 0;
                }
            });
            girl.sort((strH1,strH2)->{
                double h1=Double.parseDouble(strH1);
                double h2=Double.parseDouble(strH2);
                if(h1>h2){
                    return 1;
                }else if(h1<h2){
                    return-1;
                }else{
                    return 0;
                }
            });
            girl.forEach(H->System.out.print(H+" "));
            System.out.println();
            boy.forEach(H->System.out.print(H+" "));
            System.out.println();
        }
        sc.close();
    }
}
