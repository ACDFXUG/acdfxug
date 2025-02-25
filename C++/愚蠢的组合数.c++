#include <iostream>
#include <format>

bool is_odd_combination(const int &n,const int &k){
    std::string_view N=std::format("{:032b}",n);
    std::string_view K=std::format("{:032b}",k);
    for(int i=0;i<32;i++){
        if(K[i]>N[i]){
            return false;
        }
    }
    return true;
}

int main(){
    int T,n,k;
    scanf("%d",&T);
    while(T-->0){
        scanf("%d%d",&n,&k);
        std::cout<<is_odd_combination(n,k)<<'\n';
    }
}