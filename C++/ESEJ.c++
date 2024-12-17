#include <iostream>
#include <random>
using DRE=std::default_random_engine;
using namespace std::string_literals;

std::string random_word(DRE &dre){
    int len=1+dre()%15;
    auto s=""s;
    for(int i=0;i<len;++i){
        s+=char('a'+dre()%26);
    }
    return s;
}

int main(){
    int a,b;
    std::cin>>a>>b;
    std::random_device rd;
    DRE dre(rd());
    int L=a+dre()%(b-a+1);
    auto ans=random_word(dre);
    for(int i=1;i<L;++i){
        ans+=" "s+random_word(dre);
    }
    std::cout<<ans<<'\n';
}