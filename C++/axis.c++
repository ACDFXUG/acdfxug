#include <iostream>

int main(){
    int n,m;
    scanf("%d%d",&n,&m);
    bool Axis[200000];
    for(int s=1;s<=m;s++){
        int l,r,t=0;
        scanf("%d%d",&l,&r);
        for(int i=l-1;i<r;i++){
            Axis[i]=true;
        }
        for(int i=0;i<n;i++){
            if(Axis[i]){
                t++;
            }
        }
        printf("%d\n",n-t);
    }
}