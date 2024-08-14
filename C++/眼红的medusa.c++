#include <iostream>
#include <map>
#include <unordered_set>

int main(){
    int n,m;
    scanf("%d%d",&n,&m);
    std::map<int,int> freq;
    std::unordered_set<int> prize;
    for(int i=0,x;i<n;i++){
        scanf("%d",&x);
        freq[i]=x;
    }
    for(int i=0,x;i<m;i++){
        scanf("%d",&x);
        prize.insert(x);
    }
    for(auto &[_,value]:freq){
        if(prize.contains(value)){
            printf("%d ",value);
        }
    }
    printf("\n");
}