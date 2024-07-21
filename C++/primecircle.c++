#include <iostream>
#include <cstring>
#include <algorithm>
using namespace std;
int n,a[50],b[50];
int Prime(int x){
    for(int j=2;j<x;j++){
        if(x%j==0){
            return 0;
        }
    }
    return 1;
}
void dfs(int x){
    if(n==x&&Prime(1+a[n-1])==1){
        cout<<"1";
        for(int i=1;i<n;i++){
            cout<<" "<<a[i];
        }
        cout<<endl;
    }
    for(int i=2;i<=n;i++){
        if(b[i]==0&&Prime(a[x-1]+i)==1){
            a[x]=i;
            b[i]=1;
            dfs(x+1);
            b[i]=0;
        }
    }
}
int main(){
    for(int k=1;cin>>n;k++){
        cout<<"Case "<<k<<":"<<endl;
        memset(b,0,sizeof(b));
        a[0]=1;
        dfs(1);
        cout<<endl;
    }
}