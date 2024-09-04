#include <iostream>
#include <vector>
#include <unordered_map>
#include <algorithm>
using namespace std;

unordered_map<char,char> getDic(string order){
    unordered_map<char,char> dic;
    for(int i=0;i<26;i++){
        dic[order[i]]=i+'a';
    }
    return dic;
}

string toNormal(string word,unordered_map<char,char> dic){
    string ans="";
    for(auto ch:word){
        ans+=dic[ch];
    }
    return ans;
}

bool isAlienSorted(vector<string>& words, string order) {
    unordered_map<char,char> dic=getDic(order);
    vector<string> normal(words.size());
    for(int i=0,l=words.size();i<l;i++){
        normal[i]=toNormal(words[i],dic);
    }
    return is_sorted(normal.begin(),normal.end());
}

int main(){
    vector<string> words={"hello","leetcode"};
    string order="hlabcdefgijkmnopqrstuvwxyz";
    cout<<isAlienSorted(words,order);
}