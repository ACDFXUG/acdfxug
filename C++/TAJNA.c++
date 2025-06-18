#include <iostream>
#include <vector>
#include <cmath>

std::pair<int,int> get_max(int n){
    int maxr=sqrt(n);
    for(int r=maxr;r>0;r--){
        if(n%r==0) return {r,n/r};
    }
    return {-1,-1};
}

int main(){
    std::string s;
    std::cin>>s;
    const auto [r,c]=get_max(s.size());
    std::string encrypt(s);
    for(int j=0;j<c;++j){
        for(int i=0;i<r;++i){
            encrypt[i*c+j]=s[j*r+i];
        }
    }
    std::cout<<encrypt<<std::endl;
}