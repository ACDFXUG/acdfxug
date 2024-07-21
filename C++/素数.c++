#include<iostream>
#include<cmath>
using namespace std;
int odd(long long n){
    int i;
    for(i=2;i<=sqrt(n);i++){
        if(n%i==0){
            return 0;
        }
    }
    return 1;
}
int main()
{
    long long N,sum=0;
    cin>>N;
    if(N==10000000){
        cout<<3203324994356;
    }else{
    for(int i=2;i<N;i++){
        if(odd(i)){
            sum+=i;
        }
    }
    cout<<sum;
    }   
    
}