#include <iostream>
#include <unordered_map>
#include <vector>
using namespace std;

vector<long long> mostFrequentIDs(vector<int>& nums, vector<int>& freq) {
    int n = nums.size();
    vector<long long> ans;
    unordered_map<int, long long> id_cnt;
    long long max_freq = 0;

    for (int i = 0; i < n; ++i) {
        int x = freq[i];
        id_cnt[nums[i]] += x;
        
        if (id_cnt[nums[i]] == 0) {
            id_cnt.erase(nums[i]);
        }

        if (id_cnt.empty()) {
            ans.push_back(0);
            max_freq = 0;
        } else {
            bool update_max = (x > 0 || max_freq == 0);
            if (update_max) {
                max_freq = 0;
                for (const auto& item : id_cnt) {
                    if (item.second > max_freq) {
                        max_freq = item.second;
                    }
                }
            }
            ans.push_back(max_freq);
        }
    }
    return ans;
}

int main(){
    vector<int> nums={2,3,2,1};
    vector<int> freq={3,2,-3,1};
    for(auto x:mostFrequentIDs(nums,freq)){
        cout<<x<<" ";
    }
}