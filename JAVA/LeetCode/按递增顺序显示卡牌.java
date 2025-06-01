package JAVA.LeetCode;

import java.util.*;

public class 按递增顺序显示卡牌 {
    static int[] deckRevealedIncreasing(int[] deck) {
        int[] rev=deck.clone();
        Arrays.sort(rev);
        Deque<Integer> card=new ArrayDeque<>(deck.length);
        for(int i=deck.length-1;i>=0;i--){
            if(card.size()==0){
                card.addFirst(rev[i]);
            }else{
                card.addFirst(card.pollLast());
                card.addFirst(rev[i]);
            }
        }
        return card.stream().mapToInt(Integer::intValue).toArray();
    }
    public static void main(String[] args) {
        int[] deck={17,13,11,2,3,5,7};
        System.out.println(Arrays.toString(deckRevealedIncreasing(deck)));
    }
}
