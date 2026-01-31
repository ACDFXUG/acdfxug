#include <iostream>

//找到最大的小于S的整数
int find_nearest_max(const double &S){
    return int(S)-(S<=0);
}

int main(){
    int T;
    std::cin>>T;
    for(double x,y;T-->0;){
        std::cin>>x>>y;
        int sx=find_nearest_max(x);
        int sy=find_nearest_max(y);
        double dx=x-sx,dy=y-sy;
        if(dx<=0.5) x=sx;
        else x=sx+1;
        if(dy<=0.5) y=sy;
        else y=sy+1;
        std::cout<<x<<" "<<y<<std::endl;
    }
}