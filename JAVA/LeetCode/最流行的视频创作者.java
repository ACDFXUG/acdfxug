package JAVA.LeetCode;

import java.util.*;

public class 最流行的视频创作者 {
    private static record Video(String id,int view){}
    static List<List<String>> mostPopularCreator(String[] creators, String[] ids, int[] views) {
        final int n=creators.length;
        Map<String,Video> bestVideo=new HashMap<>(n);
        Map<String,Long> popularity=new HashMap<>(n);
        for(int i=0;i<n;++i){
            bestVideo.merge(creators[i],new Video(ids[i],views[i]),(v1,v2)->{
                if(v1.view==v2.view){
                    return v1.id.compareTo(v2.id)<0?v1:v2;
                }else{
                    return v1.view>v2.view?v1:v2;
                }
            });
            popularity.merge(creators[i],(long)views[i],Long::sum);
        }
        List<List<String>> ans=new ArrayList<>();
        long maxPopularity=popularity.values().stream().max(Long::compareTo).get();
        popularity.forEach((name,pop)->{
            if(pop==maxPopularity){
                ans.add(List.of(name,bestVideo.get(name).id));
            }
        });
        return ans;
    }
    public static void main(String[] args) {
        String[] creators={"alice","bob","alice","chris"},
                ids={"one","two","three","four"};
        int[] views={5,10,5,4};
        System.out.println(mostPopularCreator(creators,ids,views));
    }
}
