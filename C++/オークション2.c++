#include <iostream>
#include <algorithm>

int main(){
    int N,K;
    long long t=0;
    std::cin>>N>>K;
    int *item=new int[N];
    for(int i=0;i<N;scanf("%d",item+i++));
    std::sort(item,item+N);
    for(int i=0;i<K;i++){
        t+=item[i]+i;
    }
    printf("%lld\n",t);
    delete[] item;
}