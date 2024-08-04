package LeetCode;

public class HTML实体解析器 {
    static String entityParser(String text) {
        return text.replaceAll("&quot;","\"")
        .replaceAll("&apos;", "\'")
        .replaceAll("&gt;", ">")
        .replaceAll("&lt;","<")
        .replaceAll("&frasl;","/")
        .replaceAll("&amp;","&");
    }
    public static void main(String[] args) {
        System.out.println(entityParser("and I quote: &quot;...&quot;"));
    }
}
