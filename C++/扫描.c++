#include <iostream>
#include <vector>
#include <map>

int main(){
    int n,k;
    std::cin>>n>>k;
    std::vector<int> a(n);
    std::map<int,int,std::greater<int>> m;
    for(int i=0;i<n;++i){
        scanf("%d",&a[i]);
        if(i<k) m[a[i]]=i;
    }
    for(int i=k;i<=n;++i){
        for(std::cout<<m.begin()->first<<'\n';
            !m.empty()&&m.begin()->second<=i-k;m.erase(m.begin()));
        if(i<n) m[a[i]]=i;
    }
}