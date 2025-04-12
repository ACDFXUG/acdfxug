#include <iostream>
using namespace std;

string baseNeg2(int n) {
    if(n==0) return "0";
    string ans="";
    for(;n;n/=-2){
        if(n&1){
            ans+='1';
            n-=1;
        }else{
            ans+='0';
        }
    }
    reverse(ans.begin(),ans.end());
    return ans;
}

int main(){
    int n;
    cin>>n;
    cout<<baseNeg2(n);
    return 0;
}