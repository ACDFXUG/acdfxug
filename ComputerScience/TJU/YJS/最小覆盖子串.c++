#include <print>
#include <unordered_map>

using namespace std;

string minWindow(string s, string t) {
    if(s.size()<t.size()) return "";
    unordered_map<char,int> need;
    for(const auto& c:t) ++need[c];
    unordered_map<char,int> window;
    int left=0,right=0,valid=0;
    int start=0,min=INT_MAX;
    for(;right<s.size();++right){
        char c=s[right];
        if(need.count(c)){
            ++window[c];
            if(window[c]==need[c]) ++valid;
        }
        while(valid==need.size()){
            if(right-left+1<min){
                min=right-left+1;
                start=left;
            }
            char d=s[left++];
            if(need.count(d)){
                if(window[d]==need[d]) --valid;
                --window[d];
            }
        }
    }
    return min==INT_MAX?"":s.substr(start,min);
}

int main(){
    string s="ADOBECODEBANC";
    string t="ABC";
    println("{}",minWindow(s,t));
}