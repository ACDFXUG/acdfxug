package Java.LeetCode;

public class 根据规则将箱子分类 {
    static String categorizeBox(int length, int width, int height, int mass) {
        boolean isBulky=false,isHeavy=false;
        if(length>=10000||width>=10000||height>=10000||(((long)length)*((long)width)*((long)height))>=1_000_000_000){
            isBulky=true;
        }
        if(mass>=100){
            isHeavy=true;
        }
        boolean isBoth=isBulky&&isHeavy
        ,isNeither=!isBulky&&!isHeavy;
        if(isBoth){
            return "Both";
        }else if(isNeither){
            return "Neither";
        }else if(isBulky&&!isHeavy){
            return "Bulky";
        }else{
            return "Heavy";
        }
    }
    public static void main(String[] args) {
        System.out.println(categorizeBox(200, 50, 800, 50));
    }
    
}
