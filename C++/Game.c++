#include <iostream>
#include <algorithm>
#include <queue>
#define DQ std::deque<int>

int main(){
    int n,x;
    std::cin>>n;
    DQ g;
    for(int i=0;i<n;i++){
        scanf("%d",&x);
        g.push_back(x);
    }
    std::sort(g.begin(),g.end());
    for(;;){
        if(g.size()!=1){
            g.pop_back();
        }else{
            break;
        }
        if(g.size()!=1){
            g.pop_front();
        }else{
            break;
        }
    }
    printf("%d\n",g[0]);
}