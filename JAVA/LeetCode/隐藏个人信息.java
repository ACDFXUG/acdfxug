package JAVA.LeetCode;

public class 隐藏个人信息 {
    static String maskPII(String s) {
        if(s.contains("@")){
            String name=s.substring(0,s.indexOf("@")).toLowerCase();
            String domain=s.substring(s.indexOf("@")+1).toLowerCase();
            return name.charAt(0)+"*****"+name.substring(name.length()-1)+"@"+domain;
        }else{
            s=s.replaceAll("[^0-9]","");
            String locale=s.substring(s.length()-4);
            int country=s.length()-10;
            return switch(country){
                case 0->"***-***-"+locale;
                case 1->"+*-***-***-"+locale;
                case 2->"+**-***-***-"+locale;
                case 3->"+***-***-***-"+locale;
                default->null;
            };
        }
    }
    public static void main(String[] args) {
        String s="1(234)567-890";
        System.out.println(maskPII(s));
    }
}
