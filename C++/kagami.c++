#include <iostream>

int main(){
    int N,t=0,*mochi=new int[101]();
    scanf("%d",&N);
    for(;N>0;N--){
        int a;
        scanf("%d",&a);
        mochi[a]++;
    }
    for(int i=1;i<=100;i++){
        if(mochi[i]>0){
            t++;
        }
    }
    printf("%d\n",t);
    delete[] mochi;
}



