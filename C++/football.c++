#include <iostream>
using namespace std;
int main(){
    for(string s;cin>>s;){
        if(s.length()<7){
            cout<<"NO"<<endl;
        }else{
            int t=0,p=0;
            string str[100];
        for(int i=0;i<s.length()-7;i++){
            str[t]=s.substr(i,7);
            t++;
        }
        for(int i=0;i<t;i++){
            for(int j=0;j<7;j++){
                if(str[i][j]!=str[i][0]){
                    p++;
                    break;
                }
            }
        }
        if(p==t){
            cout<<"NO"<<endl;
        }else{
            cout<<"YES"<<endl;
        }
        }
    }
}