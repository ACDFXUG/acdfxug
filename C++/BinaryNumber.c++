#include <iostream>
using namespace std;
long long power(int x,int n){
    if(n==0){
        return 1;
    }else{
        long long p=1;
        for(int i=1;i<=n;i++){
            p*=x;
        }
        return p;
    }
}
long long BinToDec(string s){
    long long x=0;
    for(int i=0;i<s.length();i++){
       if(s[i]=='1'){
        x+=power(2,s.length()-i-1);
       }
    }
    return x;
}
int BN(string s){
    int t=0;
        long long x=BinToDec(s);
        for(;x!=1;){
            if(x%2==1){
                x++;
                t++;
            }else if(x%2==0){
                x/=2;
                t++;
            }
        }
        return t;
}
int main(){
    for(string S;cin>>S;){
        cout<<BN(S)<<endl;
    }
}