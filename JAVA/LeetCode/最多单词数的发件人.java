package Java.LeetCode;

import java.util.*;

public class 最多单词数的发件人 {
    static int wordCount(String message){
        return message.split("\\s").length;
    }
    static String largestWordCount(String[] messages, String[] senders) {
        int n=messages.length;
        Map<String,Integer> words=new HashMap<>();
        for(int i=0;i<n;i++){
            int cnt=wordCount(messages[i]);
            words.put(senders[i],words.getOrDefault(senders[i],0)+cnt);
        }
        return words.entrySet().stream()
        .min((E1,E2)->{
            int cnt1=E1.getValue();
            int cnt2=E2.getValue();
            return cnt1==cnt2?
            E2.getKey().compareTo(E1.getKey()):cnt2-cnt1;
        }).get().getKey();
    }
    public static void main(String[] args) {
        String[] messages={"Hello userTwooo","Hi userThree","Wonderful day Alice","Nice day userThree"};
        String[] senders={"Alice","userTwo","userThree","Alice"};
        System.out.println(largestWordCount(messages, senders));
    }
}
