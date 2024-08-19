#include <iostream>
#include <unordered_map>
#include <list>
#include <algorithm>
#include <queue>


struct Country{
    int Pi,Yi;
    Country(int Pi,int Yi):Pi(Pi),Yi(Yi){}
};

int indexOf(std::priority_queue<int> pq,int &x){
    while(!pq.empty()){
        if(pq.top()==x){
            return pq.size()-1;
        }
        pq.pop();
    }
    return -1;
}

int main(){
    int M,N;
    std::cin>>M>>N;
    std::list<Country> ctry;
    std::unordered_map<int,std::priority_queue<int>> sorted;
    while(N-->0){
        int Pi,Yi;
        scanf("%d%d",&Pi,&Yi);
        ctry.push_back(Country(Pi,Yi));
        sorted[Pi].push(Yi);
    }
    for(Country &st:ctry){
        int pi=st.Pi,yi=st.Yi;
        std::priority_queue<int> pq(sorted[pi]);
        int index=indexOf(pq,yi);
        printf("%06d%06d\n",pi,index+1);
    }
}