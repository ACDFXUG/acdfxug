#include <iostream>
#include <print>
#include <unordered_map>

int main(){
    std::unordered_map<std::string,std::pair<int,int>> warehouse;
    int n,m,k;
    scanf("%d%d%d",&n,&m,&k);
    bool *const location=new bool[m*n](false);
    while(k-->0){
        int act;
        scanf("%d",&act);
        if(act==1){
            int x,y;
            std::string id;
            scanf("%d%d",&x,&y);
            std::cin>>id;
            int idx=m*(x-1)+(y-1);
            if(location[idx]){
                for(int i=idx;i<m*n;i++){
                    if(!location[i]){
                        location[i]=true;
                        warehouse[id]={i/m+1,i%m+1};
                        break;
                    }
                }
            }else{
                location[idx]=true;
                warehouse[id]={x,y};
            }
        }else if(act==-1){
            std::string id;
            std::cin>>id;
            if(warehouse.contains(id)){
                const auto &[x,y]=warehouse[id];
                std::println("{} {}",x,y);
                location[m*(x-1)+(y-1)]=false;
                warehouse.erase(id);
            }else{
                std::println("-1 -1");
            }
        }
    }
    delete[] location;
}