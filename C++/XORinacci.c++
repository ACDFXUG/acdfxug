#include <iostream>
using namespace std;
int main(){
    int T;
    cin>>T;
    for(int m=1,a,b,n;cin>>a>>b>>n&&m<=T;m++){
        if(n%3==0){
            cout<<a<<endl;
        }else if(n%3==1){
            cout<<b<<endl;
        }else if(n%3==2){
            cout<<(a^b)<<endl;
        }         
    }
    
}