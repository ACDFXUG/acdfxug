#include <iostream>

int main(){
    int N;
    std::cin>>N;
    int *a=new int[N](0);
    for(int x,i=0;i<N-1;i++){
        scanf("%d",&x);
        a[x-1]=1;
    }
    for(int i=0;i<N;i++){
        if(a[i]==0){
            printf("%d\n",i+1);
            break;
        }
    }
}