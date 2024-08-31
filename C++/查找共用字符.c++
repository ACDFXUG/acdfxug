#include <iostream>
#include <vector>
using namespace std;

vector<string> commonChars(vector<string>& words) {
    vector<string> ans;
    int n=words.size(),cnt[26]{};
    for(string &str:words){
        for(char &ch:str){
            cnt[ch-'a']++;
        }
    }
    for(int i=0;i<26;i++){
        for(int j=0;j<cnt[i]/n;j++){
            ans.push_back(string(1,i+'a'));
        }
    }
    return ans;
}

int main(){

}