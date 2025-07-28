#include <iostream>
#include <vector>

std::pair<int,int> critic(const std::vector<std::vector<int>> &crtc,int i){
    if(i==1) return {1,2};
    else{
        const auto [u,v]=critic(crtc,i-1);
        return {v,crtc[v-1][u-1]};
    }
}

int main(){
    int n,k;
    std::cin>>n>>k;
    std::vector<std::vector<int>> crtc(n,std::vector<int>(n));
    for(int i=0;i<n;++i){
        for(int j=0;j<n;++j){
            std::cin>>crtc[i][j];
        }
    }
    std::vector<std::pair<int,int>> rec;
    for(int i=1;;++i){
        auto p=critic(crtc,i);
        rec.push_back(p);
        if(i>=3){
            if(p==rec[1]){
                break;
            }
        }
    }
    int recu=rec.size()-2;
    int idx=(k-2)%recu;
    std::cout<<rec[idx+1].first<<'\n';
}