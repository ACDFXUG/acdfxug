package Java.Luogu;

import java.util.*;

public class Barrel {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int S=sc.nextInt(),
        H=sc.nextInt(),
        V=sc.nextInt();
        double h0=V*1.0/S,v=0;
        int n=sc.nextInt();
        for(int i=0;i<n;i++){
            double L=sc.nextDouble(),V0=L*L*L;
            double p=sc.nextDouble();
            v+=p<=1?p*V0:V0;
        }
        h0+=v/S;
        System.err.printf("%.7f",h0>=H?H:h0);
        sc.close();
    }
}
