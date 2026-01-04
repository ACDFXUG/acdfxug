package Java.LeetCode;

import java.util.Arrays;
import java.util.TreeMap;

public class 设计一个ATM机器 {
    private static class ATM {
        TreeMap<Integer,Integer> cash;
        final int[] denoms={500,200,100,50,20};
        public ATM() {
            this.cash=new TreeMap<>((a,b)->b-a){{
                for(int i=0;i<5;put(denoms[i++],0));
            }};
        }
        
        public void deposit(int[] banknotesCount) {
            cash.merge(20,banknotesCount[0],Integer::sum);
            cash.merge(50,banknotesCount[1],Integer::sum);
            cash.merge(100,banknotesCount[2],Integer::sum);
            cash.merge(200,banknotesCount[3],Integer::sum);
            cash.merge(500,banknotesCount[4],Integer::sum);
        }
        
        public int[] withdraw(int amount) {
            int[] res=new int[5];
            var copy=new TreeMap<>(cash);
            for(int i=0;i<5;++i){
                int denom=denoms[i];
                while(amount>=denom&&copy.get(denom)>0){
                    amount-=denom;
                    ++res[4-i];
                    copy.merge(denom,-1,Integer::sum);
                }
            }
            if(amount==0){
                cash=copy;
                return res;
            }else{
                return new int[]{-1};
            }
        }
    }
    public static void main(String[] args) {
        ATM atm=new ATM();
        atm.deposit(new int[]{0,0,1,2,1});
        System.out.println(Arrays.toString(atm.withdraw(600)));
        atm.deposit(new int[]{0,1,0,1,1});
        System.out.println(Arrays.toString(atm.withdraw(600)));
        System.out.println(Arrays.toString(atm.withdraw(550)));
    }
}
