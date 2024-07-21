#include <iostream>
#include <cmath>
using namespace std;
int b[10000000];
unsigned long long primesum(int n){
    unsigned long long sum=0;
    for(int i=2;i*i<=n;i++) {
        if(b[i]==0){
            for(int j=i*i;j<=n;j+=i){
                b[j]=1;
            }
        }
    }
    for(int i=2;i<=n;i++){
        if(b[i]==0){
            sum+=i;
        }
    }
    return sum;
}
int main(){
    int n;
    cin>>n;
    cout<<primesum(n)<<endl;
}