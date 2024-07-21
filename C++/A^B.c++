#include <iostream>
using namespace std;
int main(){ 
    for(int a,b;cin>>a>>b&&(a||b);){
        int k=1;
        for(int i=0;i<b;i++){
            k*=a;
            if(k>1000){
                k%=1000;
            }
        }
        cout<<k<<endl;
    }
}