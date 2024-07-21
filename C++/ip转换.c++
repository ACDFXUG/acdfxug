#include <iostream>
#include <cstring>
#include <cmath>
using namespace std;
int main()
{
    int T;
    cin>>T;
    for(int i=1;i<=T;i++){
        string s;
        cin>>s;
        char *p=new char[s.length()];
        strcpy(p,s.c_str());
        int a=0,b=0,c=0,d=0;
        for(int i=0;i<8;i++){
            if(p[i]==49){
                a+=pow(2,7-i);
            }
        }
        for(int i=8;i<16;i++){
            if(p[i]==49){
                b+=pow(2,15-i);
            }
        }
        for(int i=16;i<24;i++){
            if(p[i]==49){
                c+=pow(2,23-i);
            }
        }
        for(int i=24;i<32;i++){
            if(p[i]==49){
                d+=pow(2,31-i);
            }
        }
        cout<<a<<"."<<b<<"."<<c<<"."<<d<<endl;
    }
}