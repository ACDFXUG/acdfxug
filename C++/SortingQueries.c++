#include <iostream>
#include <queue>
#include <algorithm>
int main(){
    std::priority_queue<int> pq;
    int N;
    std::cin >> N;
    for(int act;N-->0;){
        scanf("%d",&act);
        if(act==1){
            int x;
            scanf("%d",&x);
            pq.push(x);
        }else if(act==2){
            printf("%d\n",pq.top());
            pq.pop();
        }else if(act==3){
        }
    }
}