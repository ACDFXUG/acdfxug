package LeetCode;

import java.util.regex.*;

public class 求解方程 {
    static Pattern NUMBER=Pattern.compile("[+-]?\\b\\d+(?!x)\\b");
    static Pattern X=Pattern.compile("([+-]?[02-9]*)x");
    static int get(String s){
        int num=0;
        for(Matcher number=NUMBER.matcher(s);number.find();){
            num+=Integer.parseInt(number.group());
        }
        return num;
    }
    static int getX(String s){
        int scalar=0;
        for(Matcher x=X.matcher(s);x.find();){
            String SC=x.group(1);
            if(SC.isEmpty()||SC.equals("+")){
                scalar+=1;
            }else if(SC.equals("-")){
                scalar-=1;
            }else{
                scalar+=Integer.parseInt(SC);
            }
        }
        return scalar;
    }
    static String solveEquation(String equation) {
        String[] both=equation.split("=");
        String left=both[0],right=both[1];
        int leftNum=get(left),rightNum=get(right);
        System.out.println(leftNum+" "+rightNum);
        int leftX=getX(left),rightX=getX(right);
        System.out.println(leftX+" "+rightX);
        int scalar=rightNum-leftNum;
        int scalarX=leftX-rightX;
        if(scalarX==0){
            return scalar==0?"Infinite solutions":"No solution";
        }else{
            return "x="+(scalar/scalarX);
        }
    }
    public static void main(String[] args) {
        String equation="0x=5";
        System.out.println(solveEquation(equation));
    }
}
