#include <iostream>
#include <vector>

int main(){
    int n,i,j;
    scanf("%d%d%d",&n,&i,&j);
    //螺旋矩阵
    std::vector<std::vector<int>> a(n,std::vector<int>(n,0));
    for(int k=1,s=0,t=0;k<=n*n;){
        while(t<n&&a[s][t]==0){
            a[s][t++]=k++;
            if(s==i&&t==j){
                printf("%d\n",k-1);
            }
        }
        s++;
        t--;
        while(s<n&&a[s][t]==0){
            a[s++][t]=k++;
        }
        s--;
        t--;
        while(t>=0&&a[s][t]==0){
            a[s][t--]=k++;
        }
        s--;
        t++;
        while(s>=0&&a[s][t]==0){
            a[s--][t]=k++;
        }
        s++;
        t++;
    }

}