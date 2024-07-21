#include <iostream>

int min(int *a,int n){
    int min=0x7fffffff;
    for(int i=0;i<n;i++){
        min=(a[i]<min)?a[i]:min;
    }
    return min;
}

int main(){
    int n;
    scanf("%d",&n);
    int *soldier=new int[n],*height=new int[n];
    for(int i=0;i<n;scanf("%d",soldier+i++));
    for(int i=0;i<n-1;i++){
        height[i]=abs(soldier[i+1]-soldier[i]);
    }
    height[n-1]=abs(soldier[0]-soldier[n-1]);
    int m=min(height,n);
    bool end=true;
    for(int i=0;i<n-1;i++){
        if(height[i]==m){
            printf("%d %d\n",i+1,i+2);
            end=false;
            break;
        }
    }
    if(end){
        printf("%d 1\n",n);
    }
}