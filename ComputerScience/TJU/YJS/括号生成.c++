#include <print>
#include <vector>

using namespace std;

void backtrack(string &path,vector<string> &res,int left,int right,int n){
    if(left==n&&right==n){ 
        res.push_back({path});
        return;
    }
    if(left<n){
        path.push_back('(');
        backtrack(path,res,left+1,right,n);
        path.pop_back();
    }
    if(right<left){
        path.push_back(')');
        backtrack(path,res,left,right+1,n);
        path.pop_back();
    }
}

vector<string> generateParenthesis(int n) {
    vector<string> res;
    string path;
    path.reserve(n<<1);
    backtrack(path,res,0,0,n);
    return res;
}

int main(){
    println("{}",generateParenthesis(3));
}