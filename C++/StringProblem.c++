#include <iostream>

#define FAST_IO do{\
    std::ios::sync_with_stdio(false);\
    std::cin.tie(nullptr);\
    std::cout.tie(nullptr);\
}while(0)

int main(){
    FAST_IO;
    int T;
    std::cin>>T;
    while(T--){
        int n,q;
        std::cin>>n>>q;
        std::string s,t;
        std::cin>>s>>t;
        int diff=0;
        for(int i=0;i<n;++i){
            if(s[i]!=t[i])
                ++diff;
        }
        std::cout<<(diff==0?"Yes\n":"No\n");
        while(q--){
            int o,p;
            char c;
            std::cin>>o>>p>>c;
            int idx=p-1;
            if(s[idx]!=t[idx]){
                --diff;
            }
            (o==0?s[idx]:t[idx])=c;
            if(s[idx]!=t[idx]){
                ++diff;
            }
            std::cout<<(diff==0?"Yes\n":"No\n");
        }
    }
}