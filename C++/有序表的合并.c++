#include <iostream>
#include <unordered_map>

int main(){
    int T;
    std::cin>>T;
    while(T-->0){
        int n,m;
        scanf("%d%d",&n,&m);
        std::unordered_map<size_t,int> freq;
        for(int i=0;i<n;i++){
            size_t x;
            scanf("%llu",&x);
            freq[x]=0;
        }
        for(int i=0;i<m;i++){
            size_t y;
            scanf("%llu",&y);
            if(freq.contains(y)){
                ++freq[y];
            }
        }
        int ans=0;
        for(auto &[_,value]:freq){
            ans^=value;
        }
        printf("%d\n",ans);
    }
}