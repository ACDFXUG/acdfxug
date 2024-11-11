#include <iostream>
#include <set>
#include <list>

int main(){
    int n,m,x,act;
    scanf("%d%d",&n,&m);
    std::list<int> a;
    for(int i=0;i<n;a.push_back(++i));
    std::set<int> b;
    while(m-->0){
        scanf("%d",&act);
        if(act==1){
            b.insert(a.front());
            a.pop_front();
        }else if(act==2){
            scanf("%d",&x);
            b.erase(x);
        }else if(act==3){
            printf("%d\n",*b.begin());
        }
    }
}