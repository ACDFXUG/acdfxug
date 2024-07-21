#include <iostream>

int main(){
    int k,r;
    std::cin>>k>>r;
    for(int n=1;;n++){
        if((k*n-r)%10==0||k*n%10==0){
            printf("%d\n",n);
            break;
        }
    }
}