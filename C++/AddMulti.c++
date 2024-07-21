#include <iostream>

int main(){
    int ans=1;
    int n,k;
    scanf("%d%d",&n,&k);
    for(;n--;ans+=(ans<k)?ans:k);
    printf("%d\n",ans);
}