#include <iostream>
#include <vector>
#include <algorithm>

struct Interval{
    int left_close,right_open;
    Interval():left_close(0),right_open(0){}
    Interval(int l,int r):
    left_close(l),right_open(r){}
    Interval &operator =(const Interval &itv){
        left_close=itv.left_close;
        right_open=itv.right_open;
        return *this;
    }
    bool operator &&(const Interval &itv){
        int min_r=std::min(right_open,itv.right_open),
            max_l=std::max(left_close,itv.left_close);
        return min_r>=max_l;
    }
    Interval operator +(const Interval &itv){
        return {
            std::min(left_close,itv.left_close),
            std::max(right_open,itv.right_open)
        };
    }
    Interval &operator +=(const Interval &itv){
        return *this=*this+itv;
    }
    bool operator <(const Interval &itv){
        return left_close<itv.left_close;
    }
};

int main(){
    int n;
    std::cin>>n;
    std::vector<Interval> itvs(n);
    for(int l,r,i=0;i<n;i++){
        scanf("%d%d",&l,&r);
        itvs[i]={l,r};
    }
    std::sort(itvs.begin(),itvs.end());
    std::vector<Interval> ans;
    ans.push_back(itvs[0]);
    for(int i=1;i<n;i++){
        auto itv=itvs[i];
        if(itv&&ans.back()){
            ans.back()+=itv;
        }else{
            ans.push_back(itv);
        }
    }
    for(auto &[left,right]:ans){
        printf("%d %d\n",left,right);
    }
}