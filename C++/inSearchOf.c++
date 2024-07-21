#include <iostream>

int main(){
    int n,t=0;
    std::cin>>n;
    for(int x,i=0;i<n;i++){
        scanf("%d",&x);
        t|=x;
    }
    printf(t?"HARD\n":"EASY\n");
}