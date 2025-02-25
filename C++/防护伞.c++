#include <iostream>
#include <queue>
#include <cmath>
#include <set>

const double PI=3.1415926535;

struct Point final{
    int x,y;
    Point(int x=0,int y=0):
    x(x),y(y){}
    double operator <=>(const Point &dest) const &{
        return hypot(x-dest.x,y-dest.y);
    }
};

int main(){
    int N;
    scanf("%d",&N);
    Point *points=new Point[N]();
    for(int i=0;i<N;i++){
        auto &[x,y]=points[i];
        scanf("%d%d",&x,&y);
    }
    std::set<double> tmp,dis;
    for(int i=0;i<N;i++){
        for(int j=0;j<N;j++){
            if(i!=j)
                tmp.insert(points[i]<=>points[j]);
        }
        dis.insert(*tmp.rbegin());
        tmp.clear();
    }
    printf("%.4lf\n",PI**dis.begin()**dis.begin());
    delete[] points;
}
