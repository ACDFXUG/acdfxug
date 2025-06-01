package JAVA.Luogu;

import java.util.*;

public class 口算练习题 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int L=sc.nextInt();
        char op='a';
        while(L-->0){
            String t=sc.next();
            switch(t){
                case "a"->{
                    op='a';
                    int l=sc.nextInt(),r=sc.nextInt();
                    String ans=String.format("%d+%d=%d",l,r,l+r);
                    System.out.println(ans);
                    System.out.println(ans.length());
                }case "b"->{
                    op='b';
                    int l=sc.nextInt(),r=sc.nextInt();
                    String ans=String.format("%d-%d=%d",l,r,l-r);
                    System.out.println(ans);
                    System.out.println(ans.length());
                }case "c"->{
                    op='c';
                    int l=sc.nextInt(),r=sc.nextInt();
                    String ans=String.format("%d*%d=%d",l,r,l*r);
                    System.out.println(ans);
                    System.out.println(ans.length());
                }default->{
                    switch(op){
                        case 'a'->{
                            int l=Integer.parseInt(t);
                            int r=sc.nextInt();
                            String ans=String.format("%d+%d=%d",l,r,l+r);
                            System.out.println(ans);
                            System.out.println(ans.length());
                        }case 'b'->{
                            int l=Integer.parseInt(t);
                            int r=sc.nextInt();
                            String ans=String.format("%d-%d=%d",l,r,l-r);
                            System.out.println(ans);
                            System.out.println(ans.length());
                        }case 'c'->{
                            int l=Integer.parseInt(t);
                            int r=sc.nextInt();
                            String ans=String.format("%d*%d=%d",l,r,l*r);
                            System.out.println(ans);
                            System.out.println(ans.length());
                        }default->System.out.println("ERROR");
                    }
                }
            }
        }
        sc.close();
    }
}
