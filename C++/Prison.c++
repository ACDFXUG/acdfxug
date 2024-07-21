#include <iostream>
#include <vector>
#include <algorithm>
#define VectorI std::vector<int>

int maxOf(VectorI A){
    std::sort(A.begin(),A.end());
    return A[A.size()-1];
}

int minOf(VectorI A){
    std::sort(A.begin(),A.end());
    return A[0];
}

int main(){
    int N,M;
    std::cin>>N>>M;
    VectorI L(M),R(M);
    for(int i=0;i<M;i++){
        scanf("%d%d",&L[i],&R[i]);
    }
    int delta=minOf(R)-maxOf(L);
    printf("%d\n",delta>=0?delta+1:0);
}


