#include <iostream>
#include <vector>

struct Point{
    int x,y;
    Point(int x=0,int y=0):
    x(x),y(y){}
    int operator <=>(const Point &dest) const &{
        return std::abs(x-dest.x)+std::abs(y-dest.y);
    }
};

int main(){
    int n,m;
    scanf("%d%d",&n,&m);
    int **bits=new int *[n];
    std::vector<Point> one;
    for(int i=0;i<n;i++){
        bits[i]=new int[m];
        for(int j=0;j<m;j++){
            scanf("%d",&bits[i][j]);
            if(bits[i][j]){
                one.push_back({i,j});
            }
        }
    }
    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            int min=1000;
            for(const auto &dest:one){
                min=std::min(min,dest<=>Point(i,j));
            }
            printf(j==m-1?"%d\n":"%d ",min);
        }
    }
    for(int i=0;i<n;i++) 
        delete[] bits[i];
    delete[] bits;
}