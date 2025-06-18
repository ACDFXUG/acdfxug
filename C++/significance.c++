#include <iostream>
#include <unordered_map>
#include <unordered_set>

template<class K>
using hashset=std::unordered_set<K>;

template<class K, class V>
using hashmap=std::unordered_map<K, V>;

int main(){
    int n;
    std::cin>>n;
    hashmap<int,hashset<int>> friends(n);
    for(int i=1,xi;i<=n;i++){
        std::cin>>xi;
        for(int yi;xi-->0;){
            std::cin>>yi;
            friends[i].insert(yi);
        }
    }
    for(int i=1;i<=n;i++){
        int frnd=friends[i].size();
        int frnds_of_frnds=0;
        for(int frnd_of_i:friends[i]){
            auto &frnds_of_frnd_of_i=friends[frnd_of_i];
            if(frnds_of_frnd_of_i.contains(i)) frnds_of_frnds+=frnds_of_frnd_of_i.size()-1;
            else frnds_of_frnds+=frnds_of_frnd_of_i.size();
        }
        std::cout<<frnd+frnds_of_frnds<<(i==n?'\n':' ');
    }
}