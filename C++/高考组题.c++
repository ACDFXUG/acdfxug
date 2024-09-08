#include <iostream>
#include <algorithm>

class Problem{
public:
    int idx;
    double avg;
    Problem():idx(0),avg(.0){}
    Problem(int index,double average):
    idx(index),avg(average){}
    bool operator <(const Problem &p) const{
        return avg==p.avg?
        idx<p.idx:avg>p.avg;
    }
    Problem &operator =(const Problem &p){
        idx=p.idx;
        avg=p.avg;
        return *this;
    }
};

int main(){
    int n,k;
    scanf("%d%d",&n,&k);
    Problem *p=new Problem[n];
    for(int i=0,x;i<n;i++){
        double avg=.0;
        for(int j=0;j<k;j++){
            scanf("%d",&x);
            avg+=x;
        }
        p[i]={i+1,avg/k};
    }
    std::sort(p,p+n);
    printf("%d\n%d\n",p[0].idx,p[1].idx);
    delete[] p;
}