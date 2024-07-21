#include <iostream>

int main(){
    int N,K,position=0;
    std::cin>>N>>K;
    int *a=new int[K];
    for(int i=0;i<K;scanf("%d",a+i++));
    for(int i=0;i<K;i++){
        int temp=position+a[i];
        if(temp<N){
            position=temp;
        }else if(temp==N){
            position=temp;
            break;
        }else if(temp>N){
            int x=temp-N;
            position=N-(a[i]-x);
        }printf("%d\n",position);
    }
    
    delete[] a;
}