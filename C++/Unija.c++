#include <iostream>
#include <vector>
#include <algorithm>

struct Rectangle{
    int width,height;
    Rectangle(int w=0,int h=0):width(w),height(h){}
    Rectangle(Rectangle &&r):width(r.width),height(r.height){}
    Rectangle(const Rectangle &r):width(r.width),height(r.height){}

    Rectangle &operator =(Rectangle &&r){
        width=r.width;
        height=r.height;
        return *this;
    }
    Rectangle &operator =(const Rectangle &r){
        width=r.width;
        height=r.height;
        return *this;
    }
    bool operator <(const Rectangle &r) const{
        return height==r.height?width<r.width:height>r.height;
    }
};

int main(){
    //给定 N 个几何中心位于平面直角坐标系原点的矩形。将这 N 个矩形进行涂色，求被涂色的总面积
    int N;
    std::cin>>N;
    std::vector<Rectangle> recs;
    recs.reserve(N);
    for(int i=0,w,h;i<N;++i){
        scanf("%d%d",&w,&h);
        recs.emplace_back(w,h);
    }
    std::sort(recs.begin(),recs.end());
    size_t area=0;
    int lastMaxW=recs[0].width;
    for(int i=0;i<N-1;++i){
        auto &r1=recs[i],&r2=recs[i+1];//r1.height>=r2.height
        area+=lastMaxW*(r1.height-r2.height);
        lastMaxW=std::max(lastMaxW,r2.width);
    }
    if(N>0) area+=lastMaxW*recs[N-1].height;
    printf("%llu\n",area);
}