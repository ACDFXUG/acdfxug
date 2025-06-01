package JAVA.LeetCode;

import java.util.*;

public class 独特的电子邮件地址 {
    private static class Email{
        String local,domain;
        Email(String email){
            String[] local$domain=email.split("@");
            int plus=local$domain[0].indexOf("+");
            this.local=(plus==-1)?local$domain[0].replaceAll("\\.",""):
            local$domain[0].substring(0,plus).replaceAll("\\.","");
            this.domain=local$domain[1];
        }
        public boolean equals(Object email){
            if(this==email){
                return true;
            }
            if(email==null||!(email instanceof Email)){
                return false;
            }
            Email e=(Email)email;
            return local.equals(e.local)
            &&domain.equals(e.domain);
        }
        public int hashCode(){
            return Objects.hash(local,domain);
        }
    }
    static int numUniqueEmails(String[] emails) {
        Set<Email> set=new HashSet<>();
        for(String email:emails){
            set.add(new Email(email));
        }
        return set.size();
    }
    public static void main(String[] args) {
        String[] emails={
            "test.email+alex@leetcode.com",
            "test.e.mail+bob.cathy@leetcode.com",
            "testemail+david@lee.tcode.com"
        };
        System.out.println(numUniqueEmails(emails));
    }
}
