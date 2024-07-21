#include <iostream>
#include <cstring>
using namespace std;
int main(){       
    int dp[101][101],a[1001]; 
    int p,q;
    for(int n;cin>>n;){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                cin>>dp[i][j];
              }
        }
        p=-1280000;
        for(int i=0;i<n;i++){
            memset(a,0,sizeof(a));
            for(int j=i;j<n;j++){   
                int sum=0;
                for(int k=0;k<n;k++){
                    a[k] += dp[j][k];
                    sum += a[k];                 
                    if(q>p){
                        p=q;
                    }
                    if(q<0){
                       q=0;
                    }
                }
            }
        }
        cout<<p<<endl;
    }
}