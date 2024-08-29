#include <iostream>
#include <unordered_set>
#include <algorithm>

int main(){
    int N,Q;
    std::cin>>N>>Q;
    int *A=new int[N]();
    std::unordered_set<int> S{};
    for(int xi,i=0;i<Q;i++){
        scanf("%d",&xi);
        if(S.contains(xi)){
            S.erase(xi);
        }else{
            S.insert(xi);
            for(int l=S.size();int x:S){
                A[x-1]+=l;
            }
        }
    }
    for(int i=0;i<N;i++){
        printf((i==N-1)?"%d\n":"%d ",A[i]);
    }
}