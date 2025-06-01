package JAVA.LeetCode;

public class 向字符串添加空格 {
    static String addSpaces(String s, int[] spaces) {
        StringBuilder space=new StringBuilder(s);
        int t=0;
        for(int index:spaces){
            space.insert(index+t++,"\s");
        }
        return space.toString();
    }
    public static void main(String[] args) {
        String s="LeetcodeHelpsMeLearn";
        int[] spaces={8,13,15};
        System.out.println(addSpaces(s,spaces));
    }
}
