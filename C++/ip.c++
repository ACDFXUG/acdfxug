#include <iostream>
#include <cstring>
#include <cmath>
using namespace std;
int power(int m,int n){
    int x=1;
    if(n==0){
        return 1;
    }else{
        for(int i=0;i<n;i++){
            x*=m;
        }
        return x;
    }
}
int main()
{
    int T;
    cin>>T;
    for(int i=1;i<=T;i++){
        string s;
        cin>>s;
        int a=0,b=0,c=0,d=0;
        for(int i=0;i<8;i++){
            if(s[i]==49){
                a+=power(2,7-i);
            }
        }
        for(int i=8;i<16;i++){
            if(s[i]==49){
                b+=power(2,15-i);
            }
        }
        for(int i=16;i<24;i++){
            if(s[i]==49){
                c+=power(2,23-i);
            }
        }
        for(int i=24;i<32;i++){
            if(s[i]==49){
                d+=power(2,31-i);
            }
        }
        cout<<a<<"."<<b<<"."<<c<<"."<<d<<endl;
    }
}


