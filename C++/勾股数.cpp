#include <iostream>
using namespace std;
int main()
{
    int a,b;
    while(cin>>a>>b){
        int p=0;
for(int i=a;i<=b;i++) {
	for(int j=a;j<=b;j++) {
		for(int k=a;k<=b;k++) {
			if(i*i+j*j==k*k&&i+j>k&&(i-j<k||j-i<k)&&i<j) {
				p++;
			}
		}
	}
}
cout<<p<<endl;
    }
}