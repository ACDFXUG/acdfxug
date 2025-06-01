package JAVA.LeetCode;

import java.util.*;

public class 窥视迭代器 {
    private static class PeekingIterator
    implements Iterator<Integer> {
        final List<Integer> it;
        int curIdx;
        PeekingIterator(Iterator<Integer> iterator) {
            this.it=new ArrayList<>();
            iterator.forEachRemaining(it::add);
            this.curIdx=0;
        }
        Integer peek() {
            return it.get(curIdx);
        }
        public Integer next() {
            return it.get(curIdx++);
        }
        public boolean hasNext() {
            return curIdx<it.size();
        }
    }
    public static void main(String[] args) {
        PeekingIterator pi=new PeekingIterator(
            List.of(1,2,3).iterator()
        );
        System.out.println(pi.next());
        System.out.println(pi.peek());
        System.out.println(pi.next());
        System.out.println(pi.next());
        System.out.println(pi.hasNext());
    }
}
