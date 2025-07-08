#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>

struct Checkpoint{
    int id;
    int x,y;
    Checkpoint(int id,int x,int y):id(id),x(x),y(y){}
    int distance(const std::pair<int,int> &stu) const{
        return abs(x-stu.first)+abs(y-stu.second);
    }
};

int main(){
    int n,m;
    std::cin>>n>>m;
    std::vector<std::pair<int,int>> students;
    students.reserve(n);
    for(int i=0,x,y;i<n;++i){
        std::cin>>x>>y;
        students.emplace_back(x,y);
    }
    std::vector<Checkpoint> checkpoints;
    checkpoints.reserve(m);
    for(int i=0,x,y;i<m;){
        std::cin>>x>>y;
        checkpoints.emplace_back(++i,x,y);
    }
    for(const auto &stu:students){
        auto compare=[&stu](const Checkpoint &cp1,const Checkpoint &cp2){
            int dis1=cp1.distance(stu);
            int dis2=cp2.distance(stu);
            return dis1==dis2?cp1.id<cp2.id:dis1<dis2;
        };
        std::sort(checkpoints.begin(),checkpoints.end(),compare);
        std::cout<<checkpoints[0].id<<'\n';
    }
}