#include <iostream>
#include <stack>
#include <map>
using namespace std;

string robotWithString(string s) {
    stack<char> S,T;
    map<char,int> freq;
    for(const char &c:s) ++freq[c];
    string ans="";
    for(int i=0;i<s.size()||!T.empty();){
        if(i<s.size()){
            S.push(s[i]);
            --freq[s[i]];
            if(freq[s[i]]==0) freq.erase(s[i]);
            ++i;
        }
        char min='z'+1;
        if(!freq.empty()) min=freq.begin()->first;
        while(!T.empty()&&T.top()<=min){
            ans+=T.top();
            T.pop();
        }
        if(!S.empty()){
            T.push(S.top());
            S.pop();
        }
    }
    return ans;
}

int main(){
    // string s;
    // cin>>s;
    cout<<robotWithString("bdda")<<endl;
    return 0;
}