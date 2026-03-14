#include <print>
#include <vector>
#include <queue>
#include <unordered_set>

using namespace std;
template<class K>
using hashset=unordered_set<K>;

int jump(vector<int>& nums) {
    const int n=nums.size();
    hashset<int> visited;
    queue<pair<int,int>> bfs;
    bfs.emplace(0,0);
    while(!bfs.empty()){
        const auto [idx,step]=bfs.front();
        bfs.pop();
        if(idx==n-1) return step;
        for(int j=0;j<=nums[idx]&&(idx+j)<n;j++){
            int nxt=idx+j;
            if(!visited.contains(nxt)){
                visited.insert(nxt);
                bfs.emplace(nxt,step+1);
            }
        }
    }
    return -1;
}

int main(){
    vector nums{2,3,1,1,4};
    println("{}",jump(nums));
}