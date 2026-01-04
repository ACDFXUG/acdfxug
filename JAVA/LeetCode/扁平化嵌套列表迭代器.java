package Java.LeetCode;

import java.util.*;

@SuppressWarnings("unused")
public class 扁平化嵌套列表迭代器 {
    private static interface NestedInteger{
        boolean isInteger();
        Integer getInteger();
        List<NestedInteger> getList();
    }
    private static class NestedIterator
    implements Iterator<Integer>{
        final List<Integer> flatList;
        int curIdx;
        final int len;
        static List<Integer> flatten(List<NestedInteger> nestedList){
            List<Integer> flatList=new ArrayList<>();
            for(var n:nestedList){
                if(n.isInteger()){
                    flatList.add(n.getInteger());
                }else{
                    flatList.addAll(flatten(n.getList()));
                }
            }
            return flatList;
        }
        NestedIterator(List<NestedInteger> nestedList) {
            this.flatList=flatten(nestedList);
            this.curIdx=0;
            this.len=flatList.size();
        }
        public Integer next() {
            return flatList.get(curIdx++);
        }
        public boolean hasNext() {
            return curIdx<len;
        }
    }
    public static void main(String[] args) {
        
    }
}
