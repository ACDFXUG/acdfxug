package LeetCode;

import java.util.*;

public class 最流行的视频创作者 {
    static List<List<String>> mostPopularCreator(String[] creators, String[] ids, int[] views) {
        Map<String,Map<String,Integer>> video=new HashMap<>(){{
            for(int i=0;i<creators.length;i++){
                Map<String,Integer> temp;
                if(containsKey(creators[i])){
                    temp=get(creators[i]);
                    temp.put(ids[i],temp.getOrDefault(ids[i],0)+views[i]);
                }else{
                    temp=new HashMap<String,Integer>();
                    temp.put(ids[i],views[i]);
                }
                put(creators[i],temp);
            }
        }};
        Map<String,Integer> sum=new HashMap<>(){{
            for(int i=0;i<creators.length;i++){
                put(creators[i],getOrDefault(creators[i],0)+views[i]);
            }
        }};
        int maxView=Collections.max(sum.values());
        List<String> maxCreators=sum.entrySet().stream()
                    .filter(E->E.getValue()==maxView)
                    .map(E->E.getKey())
                    .toList();
        List<String> maxIds=new ArrayList<>();
        for(String creator:maxCreators){
            Map<String,Integer> t=video.get(creator);
            int maxV=Collections.max(t.values());
            maxIds.add(t.entrySet().stream()
                        .filter(E->E.getValue()==maxV)
                        .sorted((E1,E2)->{
                            if(E1.getValue()==E2.getValue()){
                                return E1.getKey().compareTo(E2.getKey());
                            }else{
                                return E2.getValue()-E1.getValue();
                            }
                        })
                        .map(E->E.getKey()).toList().get(0));
        }
        List<List<String>> ans=new ArrayList<>();
        for(String c:maxCreators){
            ans.add(new ArrayList<>(){{
                add(c);
                add(maxIds.get(maxCreators.indexOf(c)));
            }});
        }
        return ans;
    }
    public static void main(String[] args) {
        String[] creators={"alice","bob","alice","chris"};
        String[] ids={"one","two","three","four"};
        int[] views={5,10,5,4};
        mostPopularCreator(creators, ids, views).forEach(L->{
            L.forEach(S->System.out.print(S+" "));
        });
    }
}
