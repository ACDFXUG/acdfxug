#include <iostream>

int main(){
    int a,b,v;
    scanf("%d%d%d",&a,&b,&v);
    for(int n=(v-a)/(a-b);n<=v*(a-b);n++){
        if(n*(a-b)+a>=v&&n*(a-b)<v){
            printf("%d\n",n+1);
            break;
        }
    }
}