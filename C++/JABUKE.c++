#include <iostream>
#include <cmath>

struct Point{
    int x,y;
    Point(int x=0,int y=0):x(x),y(y){}
    // 求两点之间距离
    double operator |(const Point &p) const{
        return std::hypot(x-p.x,y-p.y);
    }
};

double triangle_area(const Point &a,const Point &b,const Point &c){
    return std::abs(a.x*(b.y-c.y)+b.x*(c.y-a.y)+c.x*(a.y-b.y))/2.;
}

int main(){
    Point A,B,C;
    scanf("%d%d%d%d%d%d",&A.x,&A.y,&B.x,&B.y,&C.x,&C.y);
    const double S=triangle_area(A,B,C);
    printf("%.1f\n",S);
    int N,cnt=0;
    scanf("%d",&N);
    Point *points=new Point[N];
    for(int i=0;i<N;i++){
        scanf("%d%d",&points[i].x,&points[i].y);
        if(std::abs(S-(triangle_area(A,B,points[i])+triangle_area(B,C,points[i])+triangle_area(C,A,points[i])))<1e-6)
            cnt++;
    }
    printf("%d\n",cnt);
    delete[] points;
}