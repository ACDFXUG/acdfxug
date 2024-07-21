#include <iostream>
#define Ull unsigned long long

Ull lucas(int n){
    Ull *lucas=new Ull[n+1];
    lucas[0]=2;
    lucas[1]=1;
    for(int i=2;i<=n;i++){
        lucas[i]=lucas[i-1]+lucas[i-2];
    }
    return lucas[n];
}

int main(){
    int n;
    scanf("%d",&n);
    std::cout<<lucas(n)<<std::endl;
}