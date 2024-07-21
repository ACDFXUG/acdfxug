#include <iostream>
#include <queue>
#define PQueue std::priority_queue

int main(){
    int n,s,t=0;
    scanf("%d%d",&n,&s);
    PQueue<int> ans;
    for(int x;n--;t+=x){
        scanf("%d",&x);
        ans.push(x);
    }
    printf(t-ans.top()<=s?"YES\n":"NO\n");
}