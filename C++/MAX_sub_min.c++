#include <iostream>
#include <set>

int main(){
    int N;
    scanf("%d",&N);
    std::set<int> cnt;
    for(int i=0,a;i<N;i++){
        scanf("%d",&a);
        cnt.insert(a);
    }
    for(int X,x;;){
        X=*cnt.rbegin();
        x=*cnt.begin();
        if(x==X){
            break;
        }else{
            cnt.erase(X);
            cnt.insert(X-x);
        }
    }
    printf("%d\n",*cnt.begin());
}