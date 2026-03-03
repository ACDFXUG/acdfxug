#include <print>
#include <vector>

using namespace std;


bool isPalindrome(string_view s,int start,int end) {
    while(start<end){
        if(s[start++]!=s[end--]) return false;
    } 
    return true;
}

void backtrack(const string &s,int start,vector<string>& path,vector<vector<string>>& res){
    if(start==s.size()){
        res.push_back({path});
        return;
    }
    for(int i=start;i<s.size();i++){
        if(isPalindrome(s,start,i)){
            path.push_back(s.substr(start,i-start+1));
            backtrack(s,i+1,path,res);
            path.pop_back();
        }
    }
}

vector<vector<string>> partition(string s) {
    vector<vector<string>> res;
    vector<string> path;
    backtrack(s,0,path,res);
    return res;
}

int main(){
    println("{}",partition("aab"));
}