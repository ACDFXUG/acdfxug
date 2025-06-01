package JAVA.LeetCode;

import java.util.*;

public class 子域名访问计数 {
    static List<String> subdomainVisits(String[] cpdomains) {
        Map<String,Integer> visit=new HashMap<>(1000);
        for(String domain:cpdomains){
            String prefix="";
            String[] str=domain.split("\\s");
            int count=Integer.parseInt(str[0]);
            String[] subdomain=str[1].split("\\.");
            for(int i=subdomain.length-1;i>=0;i--){
                prefix=subdomain[i]+prefix;
                visit.put(prefix,visit.getOrDefault(prefix,0)+count);
                if(i!=0) prefix="."+prefix;
            }
        }
        return new ArrayList<String>(){{
            visit.forEach((sub,cnt)->add(cnt+" "+sub));
        }};
    }
    public static void main(String[] args) {
        String[] cpdomains={
            "900 google.mail.com",
            "50 yahoo.com",
            "1 intel.mail.com",
            "5 wiki.org"
        };
        System.out.println(subdomainVisits(cpdomains));
    }
}
