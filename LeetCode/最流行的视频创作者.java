package LeetCode;

import java.util.*;

public class 最流行的视频创作者 {
    static List<List<String>> mostPopularCreator(String[] creators, String[] ids, int[] views) {
        final var n=creators.length;
        Map<String,Map<String,Integer>> videos=new HashMap<>(n);
        for(int i=0;i<n;i++){
            videos.computeIfAbsent(creators[i],$->new HashMap<>())
                .merge(ids[i],views[i],Integer::sum);
        }
        Map<String,Integer> popularity=new HashMap<>(n);
        for(var creator:creators){
            var sub=videos.get(creator);
            int sum=sub.values().stream().reduce(0,Integer::sum);
            popularity.put(creator,sum);
        }
        return null;
    }
    public static void main(String[] args) {
        String[] creators={"alice","bob","alice","chris"};
        String[] ids={"one","two","three","four"};
        int[] views={5,10,5,4};
        mostPopularCreator(creators, ids, views).forEach(System.out::println);
    }
}
