#include <iostream>
#include <map>
#include <unordered_map>
#include <print>

struct Country{
    int Pi,Yi;
    Country(int pi=0,int yi=0):
    Pi(pi),Yi(yi){}
    Country &operator =(const Country &cnt){
        Pi=cnt.Pi;
        Yi=cnt.Yi;
        return *this;
    }
};

int main(){
    int M,N;
    scanf("%d%d",&M,&N);
    Country *cntry=new Country[N];
    std::unordered_map<int,std::map<int,int>> cntry_map;
    for(int i=0,Pi,Yi;i<N;i++){
        scanf("%d%d",&Pi,&Yi);
        cntry[i]={Pi,Yi};
        cntry_map[Pi][Yi]=0;
    }
    for(auto &[I,MAP]:cntry_map){
        for(int i=1;auto &[Yi,IDX]:MAP){
            IDX=i++;
        }
    }
    for(int i=0;i<N;i++){
        auto &cnt=cntry[i];
        int &pi=cnt.Pi,&yi=cnt.Yi,&idx=cntry_map[pi][yi];
        std::println("{0:06d}{1:06d}",pi,idx);
    }
    delete[] cntry;
}