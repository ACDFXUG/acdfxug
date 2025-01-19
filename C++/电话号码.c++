#include <iostream>
using namespace std;
typedef unsigned long long uz;
int PHONENUMBER(uz pn){
    string s=to_string(pn);
    int m=0,n=0,t=0;
    for(int i=0;i<11;i++){
        if(s[i]=='8'){
            m++;
        }else if(s[i]=='4'){
            n++;
        }else if(i<9&&s[i]==s[i+1]&&s[i+1]==s[i+2]){ 
            t++;
        }
    }
    return !!t&&!(!!m&&!!n)?1:0;
}
int main(){
    uz L,R;
    cin>>L>>R;
    int sum=0;
    for(uz i=L;i<=R;i++){
        if(PHONENUMBER(i)){
            sum++;
        }
    }
    cout<<sum;
}