#include <iostream>
using namespace std;
int main(){
    int T;
    cin>>T;
    for(int i=1;i<=T;i++){
        string a,b;
        cin>>a>>b;
        int p=a.find(b);
        cout<<p<<endl;
    }
}