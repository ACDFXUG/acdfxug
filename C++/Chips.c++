#include <iostream>
using namespace std;
int main(){
    for(int n,m;cin>>n>>m;){
        int *p=new int[2*n+1];
        for(int i=1;i<=n;i++){
            p[i+n]=p[i]=i;
        }
        int sum=0,s;
        for(int i=1;i<=2*n;i++){
            if(sum<=m){
                sum+=p[i];
            }else{
                s=i;
                break;
            }
        }
        sum=sum-p[s];
        int t=m-sum;
    }
}
