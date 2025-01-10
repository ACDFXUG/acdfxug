#include <iostream>
#include <format>
#include <vector>

int main(){
    int n,m=0;
    scanf("%d",&n);
    std::string_view bits=std::format("{:b}",n);
    auto v=std::vector<int>(bits.size());
    for(int i=bits.size()-1,l=i;i>=0;i--){
        if(bits[i]=='1'){
            v[m++]=1<<(l-i);
        }
    }
    printf("%d\n",m);
    std::for_each_n(v.begin(),m,[](int &x){printf("%d\n",x);});
}