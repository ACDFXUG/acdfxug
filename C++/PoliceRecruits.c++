#include <iostream>

int main(){
    int n;
    scanf("%d",&n);
    int police=0,crime=0;
    for(int i=0;i<n;i++){
        int x;
        scanf("%d",&x);
        if(x<0&&police==0){
            crime++;
            continue;
        }
        if(x>0){
            police+=x;
        }else{
            police--;
        }
    }
    printf("%d\n",crime);
}