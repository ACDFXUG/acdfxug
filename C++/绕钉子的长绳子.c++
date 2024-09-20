#include <iostream>
#include <cmath>
#define PI 3.14159265358979

struct Point{
    double x,y;
    double distance(Point p2){
        return sqrt((p2.x-x)*(p2.x-x)+(p2.y-y)*(p2.y-y));
    }
};

int main(){
    int N;
    double R;
    std::cin>>N>>R;
    double ans=2*PI*R;
    Point *p=new Point[N];
    for(int i=0;i<N;i++){
        scanf("%lf%lf",&p[i].x,&p[i].y);
    }
    for(int i=0;i<N-1;i++){
        ans+=p[i].distance(p[i+1]);
    }
    ans+=p[0].distance(p[N-1]);
    printf("%.2f\n",ans);
    delete[] p;
}