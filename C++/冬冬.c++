#include <iostream>
using namespace std;

int main(){    
    unsigned long long a[3100];
    a[0]=1;
    a[1]=2;
    a[2]=4;
    for(int i=3;i<3001;i++){
        a[i]=a[i-1]+a[i-2]+a[i-3];
    }
    for(int n;cin>>n;){
        cout<<a[n-1]<<endl;
    }
}