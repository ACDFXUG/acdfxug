#include <iostream>
#include <unordered_set>
template<typename T>
using HashSet=std::unordered_set<T>;
int main(){
    int N,M,B,G;
    scanf("%d%d%d%d",&N,&M,&B,&G);
    int lattice=0;
    HashSet<int> not_swept,swept_col;
    for(int i=1;i<=N;i++){
        not_swept.insert(i);
    }
    for(int i=1,x,y;i<=B;i++){
        scanf("%d%d",&x,&y);
        for(int j=x;j<=y;j++){
            if(not_swept.contains(j)){
                lattice+=M;
                not_swept.erase(j);
            }
        }
    }
    for(int i=1,x,y;i<=G;i++){
        scanf("%d%d",&x,&y);
        for(int j=x;j<=y;j++){
            swept_col.insert(j);
        }
    }
    lattice+=not_swept.size()*swept_col.size();
    printf("%d\n",lattice);
}