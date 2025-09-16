package JAVA.LeetCode;

public class 不含AAA或BBB的字符串 {
    static String strWithout3a3b(int a, int b) {
        StringBuilder str=new StringBuilder(a+b);
        int turnA=0,turnB=0;
        while(a!=b){
            if(a>b&&turnA<2){
                str.append('a');
                ++turnA;
                turnB=0;
                --a;
            }else if(b>a&&turnB<2){
                str.append('b');
                ++turnB;
                turnA=0;
                --b;
            }else if(a>b&&turnA==2){
                str.append('b');
                turnA=0;
                --b;
                turnB=1;
            }else if(b>a&&turnB==2){
                str.append('a');
                turnB=0;
                --a;
                turnA=1;
            }
        }
        if(str.isEmpty()){
            while(a>0){
                str.append('a');
                str.append('b');
                --a;
            }
        }else if(str.charAt(str.length()-1)=='a'){
            while(a>0){
                str.append('b');
                str.append('a');
                --a;
            }
        }else{
            while(b>0){
                str.append('a');
                str.append('b');
                --b;
            }
        }
        return str.toString();
    }
    public static void main(String[] args) {
        System.out.println(strWithout3a3b(1,1));
    }
}
