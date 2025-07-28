#include <iostream>
#include <vector>
#include <unordered_map>

int main(){
    int L;
    std::cin>>L;
    std::vector<int> gender(L);
    for(int i=0;i<L;std::cin>>gender[i++]);
    std::vector<int> prefix(L+1,0);
    std::unordered_map<int,int> mp;
    mp[0]=0;
    int max_len=0;
    for(int i=1;i<=L;++i){
        prefix[i]=prefix[i-1]+(gender[i-1]?1:-1);
        if(mp.contains(prefix[i])){
            max_len=std::max(max_len,i-mp[prefix[i]]);
        }else{
            mp[prefix[i]]=i;
        }
    }
    std::cout<<max_len<<std::endl;
}
