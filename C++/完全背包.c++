#include <iostream>
#include <cstring>
using namespace std;
long long dp[77777];
long long a[3000][2];
long long maximum(long long a,long long b){
    return (a>b)?a:b;
}
void print(long long s){
    if(s==-1){
        cout<<"NO"<<endl;
    }else{
        cout<<s<<endl;
    }
}
int main(){
    int q;
    cin>>q;
    for(int m,n;cin>>n>>m&&q>=0;q--){
        memset(dp,-1,sizeof(dp));
        dp[0]=0;
        for(int i=0;i<n;i++){
            cin>>a[i][0]>>a[i][1];
        }
        for(int i=0;i<n;i++){
            for(int j=a[i][0];j<=m;j++){
                if(dp[j-a[i][0]]!=-1){
                    dp[j]=maximum(dp[j],dp[j-a[i][0]]+a[i][1]);
                }
            }
        }
        print(dp[m]);
    }
}