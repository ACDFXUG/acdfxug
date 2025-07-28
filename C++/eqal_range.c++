#include <iostream>
#include <set>

int main(){
    std::ios::sync_with_stdio(false);
    std::cin.tie(nullptr);
    std::cout.tie(nullptr);

    int n,q;
    std::cin>>n>>q;
    std::set<int> a;
    for(int x,i=0;i<n;++i){
        std::cin>>x;
        a.insert(x);
    }
    for(int x;q-->0;){
        std::cin>>x;
        auto it1=a.upper_bound(x);//>x的最小
        auto it2=a.lower_bound(x);//<x的最大
        if(it1==a.end()) std::cout<<-1<<' ';
        else std::cout<<*it1<<' ';
        if(it2==a.begin()) std::cout<<-1<<'\n';
        else std::cout<<*std::prev(it2)<<'\n';
    }
}