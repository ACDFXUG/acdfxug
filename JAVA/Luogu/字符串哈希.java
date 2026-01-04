package Java.Luogu;

public class 字符串哈希 {
    static int stringHash(String s){
        int hash=0;
        for(int i=0;i<s.length();i++){
            hash=(hash*31+s.charAt(i))%1000000007;
        }
        return hash;
    }
    public static void main(String[] args) {
        
    }
}
