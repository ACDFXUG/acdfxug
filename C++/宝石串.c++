#include <iostream>
#include <vector>
#include <unordered_map>

auto map(std::string_view sv){
    std::vector<int> vec(sv.size());
    for(int i=0;i<sv.size();++i){
        vec[i]=(sv[i]=='G'?1:-1);
    }
    return vec;
}

int main(){
    std::string gem;
    std::cin>>gem;
    auto gem_vec=map(gem);
    std::vector<int> prefix(gem_vec.size()+1);
    for(int i=0;i<gem_vec.size();++i){
        prefix[i+1]=prefix[i]+gem_vec[i];
    }
    int max_len=0;
    std::unordered_map<int,int> first;
    for(int j=0;j<=gem_vec.size();++j){
        int pre=prefix[j];
        if(first.contains(pre)){
            int i=first[pre];
            max_len=std::max(max_len,j-i);
        }else{
            first[pre]=j;
        }
    }
    std::cout<<max_len<<std::endl;
}