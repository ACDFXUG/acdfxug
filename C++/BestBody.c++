#include <iostream>

int main(){
    int N,S,T;
    scanf("%d%d%d",&N,&S,&T);
    int W,ans=0;
    scanf("%d",&W);
    if(W>=S&&W<=T){
        ans++;
    }
    for(int i=1,dw;i<N;i++){
        scanf("%d",&dw);
        W+=dw;
        if(W>=S&&W<=T){
            ans++;
        }
    }
    printf("%d\n",ans);
}