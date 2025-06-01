package JAVA.Luogu;

import java.util.Scanner;

public class 小学数学N合1 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int T=sc.nextInt();
        switch(T){
            case 1:System.out.println("I love Luogu!");break;
            case 2:System.out.println(6+" "+4);break;
            case 3:System.out.printf("%d\n%d\n%d\n",3,12,2);break;
            case 4:System.out.printf("%.3f\n",500.0/3);break;
            case 5:System.out.println(15);break;
            case 6:System.out.println((int)Math.hypot(6, 9));break;
            case 7:System.out.printf("%d\n%d\n%d\n",110,90,0);break;
            case 8:System.out.printf("%f\n%f\n%f\n",2*3.141593*5,3.141593*25,4.0/3*3.141593*125);break;
            case 9:System.out.println(22);break;
            case 10:System.out.println(9);break;
            case 11:System.out.println(100/3);break;
            case 12:System.out.printf("%d\n%c\n",'M'-'A'+1,'A'+17);break;
            case 13:System.out.println((int)StrictMath.cbrt(4.0/3*3.141593*(4*4*4+10*10*10)));break;
            case 14:System.out.println(50);break;
        }
        sc.close();
    }
}
