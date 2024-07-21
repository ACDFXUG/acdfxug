#include <iostream>
using namespace std;
int PRIME(long long n){
    for(int j=2;j<n;j++){
        if(n%j==0){
            return 0;
        }
    }
    return 1;
}
int main(){
    int sum=0,n;
    cin>>n;
    for(int i=2;i<n;i++){
        if(PRIME(i)){
            sum++;
        }
    }
    cout<<sum;
}