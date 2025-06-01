package JAVA.LeetCode;

import java.util.*;

public class 最大频率栈 {
    @SuppressWarnings("unused")
    private static class FreqStack{
        LinkedHashMap<Integer,Integer> freq;
        FreqStack(){
            this.freq=new LinkedHashMap<>();
        }
        void push(int val) {
            freq.merge(val,1,Integer::sum);
        }
        int pop() {
            int max=0;
            for(int i:freq.values()){
                max=Math.max(max,i);
            }
            var it=freq.reversed().entrySet().iterator();
            for(;it.hasNext();){
                var en=it.next();
                if(en.getValue()==max){
                    int f=freq.get(en.getKey());
                    if(f==1){
                        freq.remove(en.getKey());
                    }else{
                        en.setValue(f-1);
                    }
                    return en.getKey();
                }
            }
            return -1;
        }
    }
    public static void main(String[] args) {
        
    }
}
