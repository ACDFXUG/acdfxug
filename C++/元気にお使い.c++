#include <iostream>
#include <print>

int main(){
    int n,sum=0;
    scanf("%d",&n);
    for(int i=1,a,b;i<=n;i++){
        scanf("%d%d",&a,&b);
        sum+=a*b;
    }
    std::println("{}",int(sum*1.05));
}