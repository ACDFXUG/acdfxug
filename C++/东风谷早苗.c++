#include <iostream>
using namespace std;

void GODDAMN(string ACTION,int T){
    int l=ACTION.length(),r=0,c=0;
    for(int i=0;i<T;i++){
        char s=ACTION[i%l];
        if(s=='E'){
            r++;
        }else if(s=='S'){
            c--;
        }else if(s=='W'){
            r--;
        }else if(s=='N'){
            c++;
        }
    }
    cout<<r<<" "<<c;
}

int main(){
    string ACTION;int T;
    cin>>ACTION>>T;
    GODDAMN(ACTION,T);
}