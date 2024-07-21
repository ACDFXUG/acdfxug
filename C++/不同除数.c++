#include <iostream>
#include <cmath>
using namespace std;
void diff(unsigned long long n){
    while(n--){
        unsigned long long a;
        cin>>a;
        unsigned long long j=0;
        for (j=sqrt(a);j<a;j++){
            if (a>=j*(j-1)&&a<j*(j+1)){
                break;
            }
        }
        if (j==1){
           cout<<1<<endl;
        }
        else{
            if(a<=j*j-1){
                cout<<2*(j-1)<<endl;
            }
            else{
                cout<<2*j-1<<endl;
            }
        }
    }
    
}
int main(){
    unsigned long long n;
    cin>>n;
    diff(n);
}