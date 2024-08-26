#include <iostream>
typedef long long ll;

class Location{
    public:
    ll x,y;
    Location():x(0ll),y(0ll){}
    Location(ll x,ll y):x(x),y(y){}
    Location &operator=(const Location &loc){
        x=loc.x;
        y=loc.y;
        return *this;
    }
    Location &operator+=(const Location &loc){
        x+=loc.x;
        y+=loc.y;
        return *this;
    }
};

//超时
int main(){
    int n,m;
    scanf("%d%d",&n,&m);
    Location *loc=new Location[n]();
    for(ll ai,bi,i=0;i<n;i++){
        scanf("%lld%lld",&ai,&bi);
        loc[i]={ai,bi};
    }
    for(int op;m-->0;){
        scanf("%d",&op);
        switch(op){
            case 1:{
                ll p,q;
                scanf("%lld%lld",&p,&q);
                for(int i=0;i<n;i++){
                    loc[i]+={p,q};
                }
                break;
            }case 2:{
                int i;
                scanf("%d",&i);
                std::swap(loc[i-1].x,loc[i-1].y);
                break;
            }case 3:{
                int i;
                scanf("%d",&i);
                printf("%lld %lld\n",loc[i-1].x,loc[i-1].y);
                break;
            }
        }
    }
    delete[] loc;
}