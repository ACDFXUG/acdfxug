#include <print>
#include <vector>
#include <stack>

using namespace std;

int largestRectangleArea(vector<int> &heights){
    stack<int> s;
    int n = heights.size(), i = 0, ans = 0;
    while(i < n){
        if(s.empty() || heights[i] >= heights[s.top()]){
            s.push(i);
            i++;
        }else{
            int t = s.top();
            s.pop();
            ans = max(ans, heights[t] * (s.empty() ? i : i - s.top() - 1));
        }
    }
    while(!s.empty()){
        int t = s.top();
        s.pop();
        ans = max(ans, heights[t] * (s.empty() ? i : i - s.top() - 1));
    }
    return ans;
}