package JAVA.LeetCode;

import java.util.*;
import java.util.stream.*;

public class 设计推特 {
    private static class Twitter{
        Map<Integer,Set<Integer>> follows;
        Map<Integer,Map<Integer,Integer>> tweets; //(userID->(time->tweetID))
        int time;
        Twitter(){
            this.time=0;
            this.follows=new HashMap<>();
            this.tweets=new HashMap<>();
        }
        void postTweet(int userId, int tweetId) {
            tweets.computeIfAbsent(
                userId,_->new HashMap<>()
            ).put(time++,tweetId);
        }
        List<Integer> getNewsFeed(int userId) {
            List<Integer> users=new ArrayList<>(){{
                add(userId);
                addAll(follows.getOrDefault(userId,new HashSet<>()));
            }};
            var timeSorted=new TreeMap<Integer,Integer>((a,b)->b-a);
            users.forEach(user->timeSorted.putAll(tweets.getOrDefault(user,new HashMap<>())));
            return timeSorted.values().stream()
                .limit(10)
                .collect(Collectors.toList());
        }
        void follow(int followerId, int followeeId) {
            follows.computeIfAbsent(
                followerId,_->new HashSet<Integer>()
            ).add(followeeId);
        }
        void unfollow(int followerId, int followeeId) {
            follows.getOrDefault(followerId,new HashSet<>()).remove(followeeId);
        }
    }
    public static void main(String[] args) {
        var tw=new Twitter();
        tw.postTweet(1,5);
        System.out.println(tw.getNewsFeed(1));
        tw.follow(1,2);
        tw.postTweet(2,6);
        System.out.println(tw.getNewsFeed(1));
        tw.unfollow(1,2);
        System.out.println(tw.getNewsFeed(1));
    }
}
