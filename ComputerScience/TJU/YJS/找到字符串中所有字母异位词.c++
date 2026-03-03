#include <iostream>
#include <vector>

using namespace std;

bool check(string s,string p) {
    int count[26]={0};
    for(int i=0;i<p.size();++i){
        count[p[i]-'a']++;
    }
    for(int i=0;i<s.size();++i){
        count[s[i]-'a']--;
    }
    for(int i=0;i<26;++i){
        if(count[i]!=0) return false;
    }
    return true;
}

vector<int> findAnagrams(string s,string p) {
    const int L=p.size();
    int left=0;
    vector<int> res;
    for(int right=L-1;right<s.size();++right){
        if(check(s.substr(right-L+1,L),p)) res.push_back(right-L+1);
    }
    return res;
}

int main() {
    string s="cbaebabacd";
    string p="abc";
    vector<int> res=findAnagrams(s,p);
    for(int i=0;i<res.size();++i){
        cout<<res[i]<<" ";
    }
    return 0;
}