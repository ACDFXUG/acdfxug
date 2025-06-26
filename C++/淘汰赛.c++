#include <iostream>
#include <vector>
#include <unordered_map>

std::vector<int> get_winner(std::vector<int> &player){
    if(player.size()==2) return player;
    std::vector<int> winner(player.size()>>1);
    for(int i=0;i<player.size();i+=2){
        winner[i>>1]=std::max(player[i],player[i+1]);
    }
    return get_winner(winner);
}

int main(){
    int n;
    std::cin>>n;
    std::vector<int> player(1<<n);
    std::unordered_map<int,int> mp;
    for(int i=0;i<(1<<n);++i){
        std::cin>>player[i];
        mp[player[i]]=i+1;
    }
    if(n==0){
        std::cout<<1<<std::endl;
        return 0;
    }
    auto last=get_winner(player);
    int min=std::min(last[0],last[1]);
    std::cout<<mp[min]<<std::endl;
}