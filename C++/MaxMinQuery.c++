#include <iostream>
#include <map>

int main(){
    std::map<int,int> counts;
    int q;
    std::cin>>q;
    while(q-->0){
        int act;
        scanf("%d",&act);
        if(act==1){
            int x;
            scanf("%d",&x);
            if(counts.contains(x)){
                counts[x]+=1;
            }else{
                counts[x]=1;
            }
        }else if(act==2){
            int x,c;
            scanf("%d%d",&x,&c);
            int d=counts[x];
            if(d>c){
                counts[x]-=c;
            }else{
                counts.erase(x);
            }
        }else if(act==3){
            int min=counts.begin()->first;
            int max=counts.rbegin()->first;
            printf("%d\n",max-min);
        }
    }
}
