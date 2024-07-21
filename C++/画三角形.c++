#include <iostream>

int sum(int n){
    int s=0;
    for(int i=0;i<n;i++){
        s+=i;
    }
    return s;
}

int main(){
    int n;
    scanf("%d",&n);
    for(int i=1;i<=n;i++){
        for(int j=1;j<=i;j++){
            printf(j==i?"%c\n":"%c",(char)((sum(i)+j-1)%26+'A'));
        }
    }
}