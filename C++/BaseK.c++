#include <iostream>
#define String std::string

int POW(int a,int x){
    int ans=1;
    for(;x--;ans*=a);
    return ans; 
}

int parseInt(String Int,int k){
    int l=Int.length(),parse=0;
    for(int i=0;i<l;i++){
        int digit=Int[i]-'0';
        parse+=digit*POW(k,l-i-1);
    }
    return parse;
}

int main(){
    int K;
    String A,B;
    std::cin>>K>>A>>B;
    unsigned long long a=parseInt(A,K),b=parseInt(B,K);
    printf("%llu\n",a*b);
}