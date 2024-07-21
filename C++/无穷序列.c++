#include <iostream>
#include <cmath>
int main(){
    int N,A;
    std::cin>>N;
    for(int i=1;i<=N;i++){
        scanf("%d",&A);
        int p=(int)sqrt((A<<1)-2);
        printf("%d\n",A==(1+p*(p+1)/2)?1:0);
    }
}