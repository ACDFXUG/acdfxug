#include <iostream>
using namespace std;
long long power(long long n,long long x){
    if(n==1){
        return x;
    }else{
        long long p=x;
        for(int i=1;i<n;i++){
            p*=x;
        }
        return p;
    }
}
int main()
{
    int n,T;
    cin>>T;    
    for(int i=0;i<T;i++){
        cin>>n;
        cout<<power(n,2)-1<<endl;
    }    
} 
