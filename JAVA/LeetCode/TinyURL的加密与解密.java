package Java.LeetCode;

import java.util.*;

public class TinyURL的加密与解密 {
    private static class TinyURL{
        Map<String,String> URL;
        TinyURL(){
            this.URL=new HashMap<String,String>();
        }
        String encode(String longUrl) {
            int index=longUrl.lastIndexOf("/");
            String left=longUrl.substring(0,index),
            right=longUrl.substring(index+1);
            String hash=String.format("%x",right.hashCode()),
            shortUrl=left+"/"+hash;
            URL.put(shortUrl,longUrl);
            return shortUrl;
        }
        String decode(String shortUrl) {
            return URL.get(shortUrl); 
        }
    }
    public static void main(String[] args) {
        TinyURL tu=new TinyURL();  
        String encode=tu.encode("https://leetcode.com/problems/design-tinyurl");
        System.out.println(encode);
        System.out.println(tu.decode(encode));
    }
}
