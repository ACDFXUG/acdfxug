#include <iostream>
#include <algorithm>
#include <numeric>

class Company{
    public:
    int ai,bi,ci,wi;
    Company():ai(0),bi(0),ci(0),wi(0){}
    Company(int a,int b,int c,int w)
    :ai(a),bi(b),ci(c),wi(w){}
    Company &operator=(const Company &cpn){
        ai=cpn.ai;
        bi=cpn.bi;
        ci=cpn.ci;
        wi=cpn.wi;
        return *this;
    }
};

class Employee{
    public:
    int xi,yi,zi;
    Employee():xi(0),yi(0),zi(0){}
    Employee(int x,int y,int z)
    :xi(x),yi(y),zi(z){}
    bool isFit(Company cpn){
        return xi>=cpn.ai&&yi>=cpn.bi&&zi>=cpn.ci;
    }
    Employee &operator=(const Employee &emp){
        xi=emp.xi;
        yi=emp.yi;
        zi=emp.zi;
        return *this;
    }
};

int main(){
    int N,M;
    std::cin>>N>>M;
    Company *cpns=new Company[N];
    for(int i=0,a,b,c,w;i<N;i++){
        scanf("%d%d%d%d",&a,&b,&c,&w);
        cpns[i]={a,b,c,w};
    }
    Employee *emps=new Employee[M];
    for(int i=0,x,y,z;i<M;i++){
        scanf("%d%d%d",&x,&y,&z);
        emps[i]={x,y,z};
    }
    std::sort(cpns,cpns+N,[](Company a,Company b){
        return a.wi>b.wi;
    });
    for(int i=0;i<M;i++){
        int max=0;
        for(int j=0;j<N;j++){
            if(emps[i].isFit(cpns[j])){
                max=max>cpns[j].wi?max:cpns[j].wi;
                break;
            }
        }
        printf("%d\n",max);
    }
    delete[] cpns,emps;
}