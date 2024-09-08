#include <iostream>

class Group{
private:
    int problem,time;
public:
    Group(int p,int t):problem(p),time(t){}
    bool operator <(Group &g){
        return problem==g.problem?
        time<g.time:problem>g.problem;
    }
    bool operator ==(Group &g){
        if(this==&g){
            return true;
        }
        return problem==g.problem&&time==g.time;
    }
};

int main(){
    int a1,p1,a2,p2;
    scanf("%d %d %d %d",&a1,&p1,&a2,&p2);
    Group g1(a1,p1),g2(a2,p2);
    if(g1<g2||g1==g2){
        printf("\"\\n\"");
    }else{
        printf("\"\\t\"");
    }
}