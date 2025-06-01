package JAVA.Luogu;

import java.util.*;
import java.util.regex.*;

@SuppressWarnings("unused")
public class 铅球杯 {
    static final Pattern chg=Pattern.compile("\\{([a-z]+)\\}");
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt(),k=sc.nextInt();
        Map<String,String> Au=new HashMap<>();
        for(int i=0;i<N;i++){
            String name=sc.next();
            String value=sc.next();
            Au.put(name,value);
        }
        String tmp=sc.nextLine();
        for(int i=0;i<k;i++){
            String line=sc.nextLine();
            Matcher m=chg.matcher(line);
            while(m.find()){
                String key=m.group(1);
                line=line.replace(m.group(),Au.get(key));
            }
            System.out.println(line);
        }
        sc.close();
    }
}
