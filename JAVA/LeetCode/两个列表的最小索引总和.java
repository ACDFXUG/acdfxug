package JAVA.LeetCode;

import java.util.*;

public class 两个列表的最小索引总和 {
    static int indexSum(List<Integer> index){
        int ans=0;
        for(int x:index){
            ans+=x;
        }
        return ans;
    }
    static String[] findRestaurant(String[] list1, String[] list2) {
        Map<String,List<Integer>> index=new HashMap<>();
        for(int i=0,l=list1.length;i<l;i++){
            int t=i;
            index.put(list1[i],new ArrayList<Integer>(){{
                add(t);
            }});
        }
        for(int i=0,l=list2.length;i<l;i++){
            if(index.containsKey(list2[i])){
                index.get(list2[i]).add(i);
            }
        }
        List<AbstractMap.SimpleEntry<String,Integer>> 
        ans=index.entrySet().stream()
        .filter(E->E.getValue().size()==2)
        .map(E->new AbstractMap.SimpleEntry<String,Integer>
            (E.getKey(),indexSum(E.getValue())))
        .sorted((E1,E2)->E1.getValue()-E2.getValue())
        .toList();
        int minIndex=ans.get(0).getValue();
        return ans.stream()
        .filter(E->E.getValue()==minIndex)
        .map(E->E.getKey())
        .toArray(String[]::new);
    }
    public static void main(String[] args) {
        String[] list1={"Shogun","Tapioca Express","Burger King","KFC"};
        String[] list2={"KFC","Shogun","Burger King"};
        System.out.println(Arrays.toString(findRestaurant(list1, list2)));
    }
}
