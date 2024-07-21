#include <iostream>
#include <iomanip>
#include <cmath>
#include <stdlib.h>
using namespace std;
int main()
{
int T,n,x,y=1;
    for(int i=1;i<=T;i++){
        cin>>n;
        for(int j=1;j<=n;j++){
            cin>>x;
            if(x%2!=0){
                y=y*x;
            }
        }
        cout<<y<<endl;
        cin>>n;
    }
}