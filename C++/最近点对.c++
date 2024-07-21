#include <iostream>
#include <cmath>
double DISTANCE(int x1,int y1,int x2,int y2){
    return sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
}
int main(){
    int n;
    scanf("%d",&n);
    int POINT[10000][2];
    for(int i=0;i<n;i++){
        scanf("%d %d",&POINT[i][0],&POINT[i][1]);
    }
    double MIN=INT_MAX;
    for(int i=0;i<n-1;i++){
        for(int j=i+1;j<n;j++){
            MIN=DISTANCE(POINT[i][0], POINT[i][1], POINT[j][0], POINT[j][1])<MIN?
                DISTANCE(POINT[i][0], POINT[i][1], POINT[j][0], POINT[j][1]):MIN;
        }
    }
    printf("%.4f\n",MIN);
}