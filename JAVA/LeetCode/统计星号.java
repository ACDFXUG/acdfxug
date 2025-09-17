package JAVA.LeetCode;

public class 统计星号 {
    static int countAsterisks(String s) {
        while(s.contains("|"))
        s=s.replaceFirst("\\|[a-z*]*\\|","");
        return s.chars().filter(ch->ch=='*')
                        .map(_->1)
                        .sum();
    }
    public static void main(String[] args) {
        String s = "l|*e*et|c**o|*de|**|e|*";
        System.out.println(countAsterisks(s));
    }
}
