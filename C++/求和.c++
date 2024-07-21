#include <iostream>
using namespace std;
int A[200000];
int SUM(int A[] ,int i,int n){
    int sum=0;
    for(int p=i+1;p<n;p++){
        sum+=A[p];
    }
    return A[i]*sum;
}
int main(){
    int n,t=0;
    cin>>n;
    for(int i=0;i<n;i++){
        cin>>A[i];
    }
    for(int i=0;i<n-1;i++){
        t+=SUM(A,i,n);
    }
    cout<<t<<"\n";
}