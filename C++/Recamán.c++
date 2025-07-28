#include <iostream>
#include <unordered_set>
#include <unordered_map>
#include <algorithm>
#include <vector>
#include <ranges>

int main(){
    std::unordered_set<int> nums;
    std::unordered_map<int,int> index;
    nums.insert(1);
    index[1]=1;
    int n;
    std::cin>>n;
    for(int k=2;k<=n;++k){
        int tmp=index[k-1]-k;
        if(!nums.contains(tmp)&&tmp>0){
            index[k]=tmp;
            nums.insert(tmp);
        }else{
            index[k]=index[k-1]+k;
        }
    }
    using namespace std::ranges;
    auto vec=index|views::values;
    std::vector v(vec.begin(),vec.end());
    std::sort(v.begin(),v.end());
    for(int i=0;i<v.size();++i){
        std::cout<<v[i]<<(i==v.size()-1?'\n':' ');
    }
}