#include <iostream>
#include <cstring>
using namespace std;
int maximum(int a,int b){
    return (a>b)?a:b;
}
int main(){
int a[1000],n,sum=0,b[1000];
cin>>n;
for(int i=0;i<n;i++){
    cin>>a[i];
    b[i]=1;
}
for(int i=1;i<n;i++){
    for(int j=0;j<i;j++){
        if(a[i]>a[j]){
            b[i]=maximum(b[i],b[j]+1);
        }
    }
}
for(int i=0;i<n;i++){
    sum=maximum(sum,b[i]);  
}
cout<<sum<<endl;
}
