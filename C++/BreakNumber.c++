#include <iostream>

const int pow2[]={1,2,4,8,16,32,64};

int main(){
    int n;
    scanf("%d",&n);
    for(int i=6;i>=0;i--){
        if(pow2[i]<=n){
            printf("%d\n",pow2[i]);
            return 0;
        }
    }
}