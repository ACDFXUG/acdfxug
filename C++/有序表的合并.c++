#include <iostream>
#include <unordered_map>

#define FAST_IO do{\
    std::ios::sync_with_stdio(false);\
    std::cin.tie(nullptr);\
    std::cout.tie(nullptr);\
}while(0)

static int n,m;
std::unordered_map<uint64_t,int> a;
uint64_t x,b,ans;

int main(){
    FAST_IO;
    int T;
    std::cin>>T;
    while(T-->0){
        std::cin>>n>>m;
        a.clear();
        a.reserve(n);
        for(int i=0;i<n;++i){
            std::cin>>x;
            a[x]=0;
        }
        for(int i=0;i<m;++i){
            std::cin>>b;
            if(a.contains(b)) ++a[b];
        }
        for(ans=0;const auto &[_,cnt]:a){
            ans^=cnt;
        }
        std::cout<<ans<<'\n';
    }
}