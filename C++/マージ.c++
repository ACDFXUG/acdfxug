#include <iostream>
#include <vector>
#include <algorithm>

int main(){
    int n,m;
    std::cin>>n>>m;
    std::vector<int> a(n);
    std::vector<int> b(m);
    for(int i=0,ai;i<n;i++){
        scanf("%d",&ai);
        a[i]=ai;
    }
    for(int i=0,bi;i<m;i++){
        scanf("%d",&bi);
        b[i]=bi;
    }
    a.insert(a.end(),b.begin(),b.end());
    std::sort(a.begin(),a.end());
    for(int x:a){
        printf("%d\n",x);
    }
}