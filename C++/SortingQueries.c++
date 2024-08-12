#include <iostream>
#include <deque>
#include <algorithm>
int main(){
    std::deque<int> A;
    int Q;
    scanf("%d",&Q);
    while(Q-->0){
        int act;
        scanf("%d",&act);
        if(act==1){
            int x;
            scanf("%d",&x);
            A.push_back(x);
        }else if(act==2){
            printf("%d\n",A.front());
            A.pop_front();
        }else if(act==3){
            std::sort(A.begin(),A.end());
        }
    }
}