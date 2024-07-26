package Luogu;

import java.util.*;
import java.util.regex.*;

public class 黑白图像压缩 {
    static final Pattern ONE=Pattern.compile("1+");
    static final Pattern ZERO=Pattern.compile("0+");
    static String binFormat(int k,int a){
        String bin=Integer.toBinaryString(a);
        while(bin.length()<k) bin="0"+bin;
        return bin;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        StringBuilder pixel=new StringBuilder();
        int n=sc.nextInt();
        for(int i=1;i<=n/8;i++){
            String bin=binFormat(8,Integer.parseInt(sc.next()));
            pixel.append(bin);
        }
        List<Integer> zip=new ArrayList<>();
        List<Integer> ones=new ArrayList<>();
        List<Integer> zeros=new ArrayList<>();
        for(Matcher one=ONE.matcher(pixel);one.find();){
            String t="1"+binFormat(7,one.group().length());
            ones.add(Integer.parseInt(t,2));
        }
        for(Matcher zero=ZERO.matcher(pixel);zero.find();){
            String t="0"+binFormat(7,zero.group().length());
            zeros.add(Integer.parseInt(t,2));
        }
        if(pixel.charAt(0)=='1'){
            for(int x=0,y=0,lx=ones.size(),ly=zeros.size();
            ;){
                if(x<lx){
                    zip.add(ones.get(x++));
                }
                if(y<ly){
                    zip.add(zeros.get(y++));
                }
                if(x==lx&&y==ly){
                    break;
                }
            }
        }else{
            for(int x=0,y=0,lx=ones.size(),ly=zeros.size();
            ;){
                if(y<ly){
                    zip.add(zeros.get(y++)); 
                }
                if(x<lx){
                    zip.add(ones.get(x++));
                }
                if(x==lx&&y==ly){
                    break;
                }
            }
        }
        for(int i=0,l=zip.size();i<l;i++){
            System.out.print(i==l-1?zip.get(i):zip.get(i)+" ");
        }
        sc.close();
    }
}
