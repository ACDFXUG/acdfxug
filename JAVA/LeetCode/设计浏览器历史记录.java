package Java.LeetCode;

import java.util.*;

public class 设计浏览器历史记录 {
    private static class BrowserHistory{
        ArrayList<String> back$current,forward;
        BrowserHistory(String homepage){
            this.back$current=new ArrayList<String>(){{
                add(homepage);
            }};
            this.forward=new ArrayList<String>();
        }
        void visit(String url){
            back$current.add(url);
            forward.clear();
        }
        String back(int steps){
            while(steps-->0&&back$current.size()>1){
                forward.add(back$current.removeLast());
            }
            return back$current.getLast();
        }
        String forward(int steps) {
            while(steps-->0&&forward.size()>0){
                back$current.add(forward.removeLast());
            }
            return back$current.getLast();
        }
    }
    public static void main(String[] args) {
        var bh=new BrowserHistory("leetcode.com");
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
