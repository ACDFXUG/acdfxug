#include <iostream>
using namespace std;

string stringHash(string s, int k) {
    string ans;
    ans.reserve(s.size()/k);
    static auto hash=[](const string &str)->int {
        int hs=0;
        for(const char &ch:str){
            hs+=ch-'a';
        }
        return hs%26;
    };
    for(int i=0;i<s.size();i+=k){
        auto sub=s.substr(i,k);
        ans+=(hash(sub)+'a');
    }
    return ans;
}

int main(){
    std::string s="sdafasfaf";
    cout<<stringHash(s,3);
}