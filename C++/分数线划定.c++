#include <iostream>
#include <algorithm>
#include <vector>

class Volunteer{
public:
    int id,score;
    Volunteer():id(0),score(0){}
    Volunteer(int id,int score):
    id(id),score(score){}
    bool operator <(const Volunteer &vol) const{
        return score==vol.score?
        id<vol.id:score>vol.score;
    }
    Volunteer &operator =(const Volunteer &vol){
        id=vol.id;
        score=vol.score;
        return *this;
    }
};

int main(){
    int n,m,count=0;
    std::cin>>n>>m;
    int line=(m*3)>>1;
    std::vector<Volunteer> vols(n),ans;
    ans.reserve(n);
    for(int i=0;i<n;i++){
        int id,score;
        scanf("%d%d",&id,&score);
        vols[i]={id,score};
    }
    std::sort(vols.begin(),vols.end());
    int level=vols[line-1].score;
    for(Volunteer &vol:vols){
        if(vol.score>=level){
            count++;
            ans.push_back(vol);
        }else{
            break;
        }
    }
    printf("%d %d\n",level,count);
    for(auto &[id,score]:ans){
        printf("%d %d\n",id,score);
    }
}