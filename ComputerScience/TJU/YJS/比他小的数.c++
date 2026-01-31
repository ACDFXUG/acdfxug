#include <iostream>

int main(){
    int n,m;
    std::cin>>n>>m;
    int ans=0;
    for(int i=1,x;i<=n;++i){
        std::cin>>x;
        if(x<m) ++ans;
    }
    std::cout<<ans<<std::endl;
}