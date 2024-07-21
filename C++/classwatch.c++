#include <iostream>
using namespace std;
int qwerty[100000];
int main(){
    int n,t=0;
    cin>>n;
    for(int x=1;x<n;x++){
        string s=to_string(x);
        int sum=0;
        for(int i=0;i<s.length();i++){
            sum+=s[i]-'0';
        }
        if(sum+x==n){
            qwerty[t]=x;
            t++;
        }
    }
    cout<<t<<endl;
    for(int i=0;i<t;i++){
        cout<<qwerty[i]<<endl;
    }
}
