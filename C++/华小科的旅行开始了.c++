#include <iostream>
#include <vector>

struct Direction{
    int x,y;
};

int main(){
    int m,n,Sx,Sy;
    scanf("%d%d%d%d",&m,&n,&Sx,&Sy);
    std::vector<std::vector<Direction>> dirs(n+1,std::vector<Direction>(m+1));
    for(int i=1;i<=n;i++){
        for(int j=1;j<=m;j++){
            int x,y;
            scanf("%d%d",&x,&y);
            dirs[i][j]={x,y};
        }
    }
    for(int lastX=Sx,lastY=Sy;Sx!=0&&Sy!=0;){
        printf("%d %d\n",Sx,Sy);
        Sx=dirs[lastX][lastY].x;
        Sy=dirs[lastX][lastY].y;
        lastX=Sx;
        lastY=Sy;
    }
}