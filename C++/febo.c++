#include <iostream>
using namespace std;
int main()
{
    int n,T;
    cin>>T;
    long long febo[60];
    febo[0]=1;febo[1]=1;
		for(int i=0;i<58;i++) {
			febo[i+2]=febo[i+1]+febo[i];
		}
    for(int i=1;i<=T;i++){
        cin>>n;
        cout<<febo[n-1]<<endl;
    }    
}