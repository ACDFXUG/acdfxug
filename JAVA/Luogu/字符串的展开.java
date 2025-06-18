package JAVA.Luogu;

import java.util.*;
import java.util.regex.*;

public class 字符串的展开 {
    static final Pattern HYPHEN=Pattern.compile("-");
    static boolean isBothLetter(char c1,char c2){
        return Character.isLetter(c1)&&Character.isLetter(c2);
    }
    static boolean isBothNumber(char c1,char c2){
        return Character.isDigit(c1)&&Character.isDigit(c2);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int p1=sc.nextInt(),p2=sc.nextInt(),p3=sc.nextInt();
        String str=sc.next();
        sc.close();
        var m=HYPHEN.matcher(str);
        StringBuilder sb=new StringBuilder();
        for(;m.find();){
            int idx=m.start();
            if(idx==0||idx==str.length()-1) m.appendReplacement(sb,"-");
            else{
                char c1=str.charAt(idx-1),c2=str.charAt(idx+1);
                if(isBothLetter(c1, c2)){
                    if(c1>=c2) m.appendReplacement(sb,"-");
                    else{
                        StringBuilder sb1=new StringBuilder();
                        switch(p3){
                            case 1->{
                                switch(p1){
                                    case 1->{
                                        for(char c=++c1;c<c2;++c){
                                            for(int i=0;i<p2;++i) sb1.append(c);
                                        }
                                    }case 2->{
                                        for(char c=(char)('A'+c1-'a'+1);c<('A'+c2-'a');++c){
                                            for(int i=0;i<p2;++i) sb1.append(c);
                                        }
                                    }case 3->{
                                        for(int i=c2-c1-1;i>0;--i){
                                            for(int j=0;j<p2;++j) sb1.append('*');
                                        }
                                    }
                                }
                                m.appendReplacement(sb,sb1.toString());
                            }case 2->{
                                switch(p1){
                                    case 1->{
                                        for(char c=--c2;c>c1;--c){
                                            for(int i=0;i<p2;++i) sb1.append(c);
                                        }
                                    }case 2->{
                                        for(char c=(char)('A'+c2-'a'-1);c>('A'+c1-'a');--c){
                                            for(int i=0;i<p2;++i) sb1.append(c);
                                        }
                                    }case 3->{
                                        for(int i=c2-c1-1;i>0;--i){
                                            for(int j=0;j<p2;++j) sb1.append('*');
                                        }
                                    }
                                }
                                m.appendReplacement(sb,sb1.toString());
                            }
                        }
                    }
                }else if(isBothNumber(c1, c2)){ 
                    if(c1>=c2) m.appendReplacement(sb,"-");
                    else{
                        StringBuilder sb1=new StringBuilder();
                        switch(p3){
                            case 1->{
                                switch(p1){
                                    case 1,2->{
                                        for(char i=++c1;i<c2;++i){
                                            for(int j=0;j<p2;++j) sb1.append(i);
                                        }
                                    }case 3->{
                                        for(int i=c2-c1-1;i>0;--i){
                                            for(int j=0;j<p2;++j) sb1.append('*');
                                        }
                                    }
                                }
                                m.appendReplacement(sb,sb1.toString());
                            }case 2->{
                                switch(p1){
                                    case 1,2->{
                                        for(char i=--c2;i>c1;--i){
                                            for(int j=0;j<p2;++j) sb1.append(i);
                                        }
                                    }case 3->{
                                        for(int i=c2-c1-1;i>0;--i){
                                            for(int j=0;j<p2;++j) sb1.append('*');
                                        }
                                    }
                                }
                                m.appendReplacement(sb,sb1.toString());
                            }
                        }
                    }
                }
            }
        }
        m.appendTail(sb);
        System.out.println(sb);
    }
}
