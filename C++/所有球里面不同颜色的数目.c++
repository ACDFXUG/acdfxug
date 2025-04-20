#include <iostream>
#include <unordered_map>
#include <unordered_set>
#include <vector>

template<class K,class V>
using hashmap=std::unordered_map<K,V>;

template<class K>
using hashset=std::unordered_set<K>;

using namespace std;

vector<int> queryResults(int limit, vector<vector<int>> &queries) {
    auto ball_color=hashmap<int,int>();
    auto color_ball=hashmap<int,hashset<int>>();
    const int n=queries.size();
    vector<int> ans(n);
    for(int i=0;i<n;i++){
        int idx=queries[i][0];
        int clr=queries[i][1];
        if(ball_color.contains(idx)){
            int old_clr=ball_color[idx];
            auto &idx_set=color_ball[old_clr];
            idx_set.erase(idx);
            if(idx_set.empty()) color_ball.erase(old_clr);
            ball_color[idx]=clr;
            color_ball[clr].insert(idx);
        }else{
            ball_color[idx]=clr;
            color_ball[clr].insert(idx);
        }
        ans[i]=color_ball.size();
    }
    return ans;
}