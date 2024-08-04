#include <iostream>

int main(){
    int n,x,ans=0;
    std::cin>>n;
    for(;n-->0;ans^=x){
        scanf("%d",&x);
    }
    printf("%d\n",ans);
}