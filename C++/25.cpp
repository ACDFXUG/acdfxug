#include <iostream>
#include <iomanip>
#include <cmath>
#include <stdlib.h>
using namespace std;
int main()
{
int n,k;
while(true){
cin>>n;
double s[1000];
double sum=0;

if(n!=0){
for(int i=1;i<=n;i++){
    cin>>k;
    s[i]=k;
    sum=sum+s[i];
}
double m=sum/n;
double l=0;
for(int i=1;i<=n;i++){
    l=l+(s[i]-m)*(s[i]-m);
}
double p=sqrt(l/n);
cout<<fixed<<setprecision(8)<<p<<endl;
}else{
    break;
}
}
}