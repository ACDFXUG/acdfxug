#include <iostream>

int main(){
    int n,first;
    scanf("%d",&n);
    auto next=new int[n];
    for(int i=0;i<n;scanf("%d",next+i++));
    scanf("%d",&first);
    printf("%d ",first);
    while(next[first-1]){
        printf("%d ",first=next[first-1]);
    }
    delete[] next;
}