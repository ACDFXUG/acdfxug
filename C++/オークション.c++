#include <iostream>
#include <algorithm>
#include <deque>
#define Deque std::deque


template<typename T> 
Deque<T> plusOne(Deque<T> item){
    Deque<T> ans;
    for(T x:item){
        ans.push_back(++x);
    }
    return ans;
}

int main(){
    int N,K,t=0;
    std::cin>>N>>K;
    Deque<int> item;
    for(int x,i=0;i<N;i++){
        scanf("%d",&x);
        item.push_back(x);
    }
    std::sort(item.begin(),item.end());
    for(;K--;){
        t+=item[0];
        item.pop_front();
        item=plusOne(item);
    }
    printf("%d\n",t);
}