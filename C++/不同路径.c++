#include <iostream>

int uniquePaths(int m, int n) {
    int **path_cnt=new int*[m];
    for(int i=0;i<m;path_cnt[i++]=new int[n]());
    for(int i=0;i<m;i++){
        for(int j=0;j<n;j++){
            if(i==0||j==0) path_cnt[i][j]=1;
            else path_cnt[i][j]=path_cnt[i-1][j]+path_cnt[i][j-1];
        }
    }
    const int ans=path_cnt[m-1][n-1];
    for(int i=0;i<m;i++) delete[] path_cnt[i];
    delete[] path_cnt;
    return ans;
}

int main(){
    int m,n;
    scanf("%d%d",&m,&n);
    printf("%d",uniquePaths(m,n));
}