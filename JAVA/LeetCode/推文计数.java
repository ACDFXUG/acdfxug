package Java.LeetCode;

import java.util.*;

public class 推文计数 {
    private static class TweetCounts {
        TreeMap<Integer,HashMap<String,Integer>> tweets;
        public TweetCounts() {
            this.tweets=new TreeMap<>();
        }
        
        public void recordTweet(String tweetName, int time) {
            var map=tweets.computeIfAbsent(time,_->new HashMap<>());
            map.merge(tweetName,1,Integer::sum);
        }
        
        public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
            List<Integer> ans=new ArrayList<>();
            final int duration=switch(freq){
                case "minute"->60;
                case "hour"->3600;
                case "day"->86400;
                default->0;
            };
            for(int i=startTime;i<=endTime;i+=duration){
                var sub=tweets.subMap(i,i+duration);
                if(sub.isEmpty()) ans.add(0);
                else ans.add(sub.values().stream()
                    .mapToInt(map->map.getOrDefault(tweetName,0))
                    .sum());
            }
            return ans;               
        }
    }

    public static void main(String[] args) {
        TweetCounts tc=new TweetCounts();
        tc.recordTweet("tweet0", 63);
        tc.recordTweet("tweet1", 50);
        tc.recordTweet("tweet2", 79);
        tc.recordTweet("tweet3", 82);
        tc.recordTweet("tweet4", 82);
        tc.recordTweet("tweet4", 85);
        System.out.println(tc.getTweetCountsPerFrequency("day", "tweet1", 85, 2011));
        System.out.println(tc.getTweetCountsPerFrequency("hour", "tweet3", 79, 3010));
        tc.recordTweet("tweet2",82);
        tc.recordTweet("tweet2",48);
        tc.recordTweet("tweet4",68);
        tc.recordTweet("tweet1",54);
    }
}
