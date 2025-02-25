#include <iostream>
#include <unordered_set>

template<class K>
using hash_set=std::unordered_set<K>;

int main(){
    int N;
    scanf("%d",&N);
    hash_set<int> num(N),sum((N*(N-1))>>1);
    for(int i=0,x;i<N;i++){
        scanf("%d",&x);
        num.insert(x);
    }
    for(const int &v:num){
        for(const int &w:num){
            if(v!=w)
                sum.insert(v+w);
        }
    }
    int ans=0;
    for(const int &s:sum){
        if(num.contains(s)){
            ++ans;
        }
    }
    printf("%d\n",ans);
}