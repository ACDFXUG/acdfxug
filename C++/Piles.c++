#include <iostream>

int main(){
    int N;
    std::cin>>N;
    int sum1=0,sum2=0;
    for(int x,i=0;i<N;i++){
        scanf("%d",&x);
        sum1+=x;
    }
    for(int x,i=0;i<N;i++){
        scanf("%d",&x);
        sum2+=x;
    }
    printf(sum2<=sum1?"Yes\n":"No\n");
}