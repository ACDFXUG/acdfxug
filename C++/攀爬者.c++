#include <iostream>
#include <cmath>
#include <algorithm>

class Mount{
public:
    int x,y,z;
    Mount():x(0),y(0),z(0){}
    Mount(int x,int y,int z):
    x(x),y(y),z(z){}
    double operator ^(const Mount &m) const{
        return sqrt(
            (x-m.x)*(x-m.x)+
            (y-m.y)*(y-m.y)+
            (z-m.z)*(z-m.z)
        );
    }
    bool operator <(const Mount &m) const{
        return z<m.z;
    }
    Mount &operator =(const Mount &m){
        x=m.x;
        y=m.y;
        z=m.z;
        return *this;
    }
};

int main(){
    int n;
    std::cin>>n;
    Mount *mou=new Mount[n];
    for(int i=0,x,y,z;i<n;mou[i++]={x,y,z}){
        scanf("%d%d%d",&x,&y,&z);
    }
    std::sort(mou,mou+n);
    double ans=.0;
    for(int i=1;i<n;i++){
        ans+=mou[i]^mou[i-1];
    }
    printf("%.3f\n",ans);
    delete[] mou;
}