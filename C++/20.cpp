#include <iostream>
#include <iomanip>
#include <cmath>
#include <stdlib.h>
using namespace std;
int power(int x,int n)
{
if(x!=-1&&n!=-1){
    long p=1;
for(int i=1;i<=n;i++){
    p*=x;
}
return p;
}
}

int main()
{
    long x, n;
    while(true){
cin>>x>>n;
if(x!=-1&&n!=-1&&x>=0&&n>=0){
    cout<<power(x,n)<<endl;
}else{
    break;
}
    }
}