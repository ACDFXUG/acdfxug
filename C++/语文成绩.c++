#include <iostream>
#include <vector>
#define FAST_IO(boo1,nul1,nul2) do{\
    std::ios::sync_with_stdio(boo1);\
    std::cin.tie(nul1);\
    std::cout.tie(nul2);\
}while(0)

int min_of_diff(const std::vector<int> &diff){
    int min=0x7fffffff,sum=0;
    for(int i=1;i<diff.size();i++){
        sum+=diff[i];
        min=std::min(min,sum);
    }
    return min;
}
int main(){
    FAST_IO(false,NULL,NULL);
    int n,p;
    std::cin>>n>>p;
    std::vector<int> a(n+1,0),diff(n+1,0);
    for(int i=1;i<=n;i++){
        std::cin>>a[i];
        if(i==1) diff[i]=a[i];
        else[[likely]] diff[i]=a[i]-a[i-1];
    }
    for(int x,y,z;p-->0;){//a[x->y]+=z
        std::cin>>x>>y>>z;
        diff[x]+=z;
        if(y+1<=n) diff[y+1]-=z;
    }
    std::cout<<min_of_diff(diff)<<std::endl;
}
