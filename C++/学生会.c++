#include <iostream>
#include <algorithm>

int main(){
    int n,m;
    scanf("%d%d",&n,&m);
    int *A=new int[m];
    for(int i=0;i<m;i++){
        scanf("%d",&*(A+i));
    }
    std::sort(A,A+m);
    for(int i=0;i<m;i++){
        printf("%d ",*(A+i));
    }
    delete[] A;
}