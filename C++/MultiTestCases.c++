#include <iostream>

int main(){
    int T;
    for(scanf("%d",&T);T--;){
        int N,t=0;scanf("%d",&N);
        for(int AN;N--;t+=AN&1){
            scanf("%d",&AN);
        }
        printf("%d\n",t);
    }
}