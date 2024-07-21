#include <iostream>
using namespace std;
int power(int x,int n){
    if(n==0){
        return 1;
    }else{
        int p=1;
        for(int i=1;i<=n;i++){
            p*=x;
        }
        return p;
    }
}
void StringToNumber(string s){
    int l=s.length();
    int *p=new int[l];
    int sum=0;
    for(int i=0;i<l;i++){
        p[i]=s[i]-'0';
        sum+=power(10,l-i-1)*p[i];
    }
    cout<<sum<<endl;
}
int main(){
    string s;
    cin>>s;
    StringToNumber(s);
}