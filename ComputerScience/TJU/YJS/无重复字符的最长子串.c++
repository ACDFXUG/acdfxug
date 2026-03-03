#include <iostream>
#include <unordered_map>

using namespace std;

// 滑动窗口
int lengthOfLongestSubstring(string s) {
    unordered_map<char,int> charCnt;
    int maxLen(0),left(0),right(0);
    for(;right<s.size();++right){
        ++charCnt[s[right]];
        while(charCnt[s[right]]>1){
            --charCnt[s[left]];
            ++left;
        }
        maxLen=max(maxLen,right-left+1);
    }
    return maxLen;
}

int main(){
    string s="pwwkew";
    cout<<lengthOfLongestSubstring(s)<<endl;
}