#include <iostream>
#include <iomanip>
#include <cmath>
#include <stdlib.h>
using namespace std;
int main()
{
    int T,m,n;
    cin>>T;
       
    cin>>m>>n;
    for(int i=1;i<=T;i++){
        int sum1=0,sum2=0;
     
        for(int i=m;i<=n;i++){
            if(i%2==0){
                     sum1=sum1+i*i;
            }
            else if(i%2==1){
                sum2=sum2+i*i*i;
            }
        }
        cout<<sum1<<" "<<sum2<<endl;
        cin>>m>>n;
    }
}