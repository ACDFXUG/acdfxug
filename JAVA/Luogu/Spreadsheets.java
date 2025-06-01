package JAVA.Luogu;

import java.util.*;
import java.util.regex.*;

public class Spreadsheets {
    static final Pattern EXCEL=Pattern.compile("([A-Z]+)(\\d+)");
    static final Pattern RXCY=Pattern.compile("R(\\d+)C(\\d+)");
    static int excelToInt(String excel){
        int L=excel.length();
        int ans=0;
        for(int i=L-1,pow=1;i>=0;i--){
            int scalar=excel.charAt(i)-'A'+1;
            ans+=scalar*pow;
            pow*=26;
        }
        return ans;
    }
    static String intToExcel(int col){
        StringBuilder sb=new StringBuilder();
        while(col>0){
            int scalar=col%26;
            if(scalar==0){
                scalar=26;
                col-=26;
            }
            sb.append((char)('A'+scalar-1));
            col/=26;
        }
        return sb.reverse().toString();
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        while(N-->0){
            String location=sc.next();
            Matcher excel=EXCEL.matcher(location);
            if(excel.matches()){
                String XY=excel.group(1);
                String row=excel.group(2);
                String col=Integer.toString(excelToInt(XY));
                System.out.println("R"+row+"C"+col);
            }else{
                Matcher rxcy=RXCY.matcher(location);
                if(rxcy.matches()){
                    String row=rxcy.group(1);
                    String col=rxcy.group(2);
                    System.out.println(intToExcel(Integer.parseInt(col))+row);
                }
            }
        }
        // System.out.println(intToExcel(N));
        sc.close();
    }
}
