package JAVA;

import java.util.*;

public class 设计浏览器历史记录 {
    private static class BrowserHistory{
        List<String> history;
        int index;
        BrowserHistory(String homepage) {
            this.history=new ArrayList<String>(){{
                add(homepage);
            }};
            this.index=0;
        }
        void visit(String url){
            if(index<history.size()-1){
                history=history.subList(0,index+1);
            }
            history.add(url);
            index=history.size()-1;
        }
        String back(int steps){
            if(index-steps<0){
                index=0;
                return history.get(0);
            }
            index-=steps;
            return history.get(index);
        }
        String forward(int steps){
            if(index+steps>=history.size()-1){
                index=history.size()-1;
                return history.get(history.size()-1);
            }
            index+=steps;
            return history.get(index);
        }
    }
    public static void main(String[] args) {
        BrowserHistory bh=new BrowserHistory("leetcode.com");
        bh.visit("google.com");
        bh.visit("facebook.com");
        bh.visit("youtube.com");
        System.out.println(bh.back(1));
        System.out.println(bh.back(1));
        System.out.println(bh.forward(1));
        bh.visit("linkedin.com");
        System.out.println(bh.forward(2));
        System.out.println(bh.back(2));
        System.out.println(bh.back(7));
    }
}
