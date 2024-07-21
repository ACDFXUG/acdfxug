#include <iostream>
#define Ull unsigned long long
#define String std::string

Ull power(int a,int x){
    Ull ans=1ull;
    for(;x--;ans*=a);
    return ans;
}

Ull f(int n){
    String N=std::to_string(n);
    Ull ans=0,l=N.length();
    for(Ull i=0ull;i<l;i++){
        ans+=(N[i]-'0')*power(n,l-i-1);
    }
    return ans;
}

int isFound(Ull a){
    for(int i=1;i<=10000;i++){
        if(f(i)==a){
            return i;
        }
    }
    return -1;
}


int main(){
    Ull a;
    std::cin>>a;
    std::cout<<isFound(a)<<std::endl;
}