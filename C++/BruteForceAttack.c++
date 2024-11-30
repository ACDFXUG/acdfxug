#include <iostream>
#include <print>

std::string to3str(int n,const int &L){
    std::string str;
    for(;n>0;n/=3){
        str=char(n%3+'a')+str;
    }
    return std::string(L-str.size(),'a')+str;
}

int pow3(int n){
    int ans=1,t=3;
    for(;n>0;n>>=1){
        if(n&1){
            ans*=t;
        }
        t*=t;
    }
    return ans;
}

int main(){
    int N;
    scanf("%d",&N);
    for(int i=0;i<pow3(N);++i){
        std::println("{}",to3str(i,N));
    }
}