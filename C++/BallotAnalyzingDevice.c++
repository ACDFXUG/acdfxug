#include <iostream>
#include <algorithm>
#include <cmath>

struct Campaigher{
    std::string name;
    int vote_cnt;
    bool operator <(const Campaigher &cam) const{
        return cam.vote_cnt<vote_cnt;
    }
    Campaigher &operator =(const Campaigher &cam){
        name=cam.name;
        vote_cnt=cam.vote_cnt;
        return *this;
    }
};

std::pair<bool,int> is_valid(std::string &vote){
    int cnt=0,lct=0;
    for(int i=0,l=vote.length();
    i<l;i++){
        if(vote[i]=='X'){
            cnt++;
            lct=i;
        }
    }
    return {cnt==1,lct};
}

double round(int x,int m){
    return std::floor(x*10000+0.5)/(100.0*m);
} 

int main(){
    int n,m,invalid=0;
    std::cin>>n>>m;
    std::string tmp;
    Campaigher *cam=new Campaigher[n];
    for(int i=0;i<n;i++){
        std::cin>>tmp;
        cam[i]={tmp,0};
    }
    for(int i=0;i<m;i++){
        std::cin>>tmp;
        auto [valid,lct]=is_valid(tmp);
        (valid?(cam+lct)->vote_cnt:invalid)++;
    }
    std::stable_sort(cam,cam+n);
    for(int i=0;i<n;i++){
        auto &[name,cnt]=cam[i];
        printf("%s %.2lf%%\n",name.c_str(),cnt*100./m);
    }
    printf("Invalid %.2lf%%\n",invalid*100./m);
}