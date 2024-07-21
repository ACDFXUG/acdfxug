#include <iostream>
using namespace std;
int A[1000][1000]={0};
int main(){
    int n,m;
    scanf("%d %d",&n,&m);
    for(int s=1;s<=m;s++){
        int x1,y1,x2,y2;
        scanf("%d %d %d %d",&x1,&y1,&x2,&y2);
        for(int i=x1-1;i<x2;i++){
            for(int j=y1-1;j<y2;j++){
                A[i][j]++;
            }
        }
    }
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            printf(j<n-1?"%d ":"%d\n",A[i][j]);
        }
    }
}
