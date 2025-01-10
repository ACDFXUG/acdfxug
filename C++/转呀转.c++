#include <iostream>
#include <cmath>
#include <print>
using ld=long double;

struct Point{
    const ld x,y;
    Point(ld x=0,ld y=0):x(x),y(y){};
    static ld distance(const Point &s,const Point &e){
        return std::hypot(s.x-e.x,s.y-e.y);
    }
};

int main(){
    int x,y;
    scanf("%d%d",&x,&y);
    ld t,v;
    scanf("%Lf%Lf",&t,&v);
    ld r=std::hypot(x,y);
    const Point s(x,y);
    ld theta_s=std::atan2(y,x);
    ld theta_delta=2*M_PI*(v*t-int(v*t));
    ld theta_e=theta_s+theta_delta;
    const Point e(r*std::cos(theta_e),r*std::sin(theta_e));
    std::println("{:.10Lf}",Point::distance(s,e));
}
