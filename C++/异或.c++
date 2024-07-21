#include <iostream>
using namespace std;
int main()
{
    long long n;
    cin>>n;
    if(n%4==0){
        cout<<n;
    }else if((n+1)%4==0){
        cout<<0;   
    }else if((n-1)%4==0){
        cout<<1;
    }else if((n+2)%4==0){
        cout<<n+1;
    }
    
}
