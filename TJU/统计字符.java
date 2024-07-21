package TJU;

import java.util.Scanner;

public class 统计字符 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for(;;){
            String a = sc.nextLine();
            if(a.equals("#")){
                sc.close();
                break;
            }
            String b = sc.nextLine();
            if(b.equals("#")){
                sc.close();
                break;
            }
            char[] c = a.toCharArray();
            char[] d = b.toCharArray();
            int[] num=new int[c.length];
            for(int i=0;i<d.length;i++){
                for(int j=0;j<c.length;j++){
                    if(d[i]==c[j]){
                        num[j]++;
                    }
                }
            }
            for(int i=0;i<c.length;i++){
                System.out.printf("%c %d\n",c[i],num[i]);
            }
        }
    }
}
