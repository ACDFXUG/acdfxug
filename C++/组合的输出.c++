#include <iostream>
#include <vector>
#include <iomanip>
#include <ranges>
#include <algorithm>

void backtrack(const int &n,const int &k,std::vector<int> &path,std::vector<std::vector<int>> &res){
    if(path.size()==k){
        res.push_back(path);
        return;
    }
    for(int i=(path.empty()?1:path.back()+1);i<=n;i++){
        path.push_back(i);
        backtrack(n,k,path,res);
        path.pop_back();
    }
}

std::vector<std::vector<int>> combination(const int &n,const int &k){
    std::vector<std::vector<int>> res;
    std::vector<int> path;
    backtrack(n,k,path,res);
    return res;
}

auto println_vec=[](const std::vector<int> &vec){
    for(auto it=vec.begin();it!=vec.end();++it){
        std::cout<<std::setw(3)<<*it;
        if(it==vec.end()-1) std::cout<<'\n';
    }
};

int main(){
    int n,r;
    std::cin>>n>>r;
    auto res=combination(n,r);
    namespace rg=std::ranges;
    rg::for_each(res,println_vec);
}