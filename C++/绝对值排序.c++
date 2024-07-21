#include <iostream>
#include <cmath>
using namespace std;
int main()
{   
    int n;
    while(cin>>n){
    int *a=new int[n];
    int *b=new int[n];
    int *c=new int[n];    
    if(n!=0){
    for(int i=0;i<n;i++){
        cin>>a[i];
        b[i]=fabs(a[i]);
    }
    int t;
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            if(b[i]>b[j]){
                t=b[i];
                b[i]=b[j];
                b[j]=t;
            }
        }
    }
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            if(fabs(a[j])==b[i]){
                c[i]=a[j];
            }
        }
    }
    for(int i=0;i<n;i++){
        cout<<c[i];
        if(i<n-1){
            cout<<" ";
        }
    }
    cout<<endl;
    }else{
        break;
    }
    }
}