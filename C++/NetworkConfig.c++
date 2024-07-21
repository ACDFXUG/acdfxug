#include <iostream>
#include <queue>
#define PQueue std::priority_queue

int *toArray(PQueue<int> a){
    int n=a.size(),*ans=new int[n];
    for(int i=0;i<n;i++){
        ans[i]=a.top();
        a.pop();
    }
    return ans;
}

int main(){
    int n,k;
    scanf("%d%d",&n,&k);
    PQueue<int> a;
    for(int x,i=0;i<n;i++){
        scanf("%d",&x);
        a.push(x);
    }
    printf("%d\n",toArray(a)[k-1]);
}