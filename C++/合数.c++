#include <iostream>
using namespace std;
int main()
{
    int n,t=0,i,j;
    cin>>n;
    for(i=2;i<=n;i++) {
	for(j=2;j<i;j++) {
		if(i%j==0) {
			break;
		}
	}
	if(j==i) {
		t++;
	}
}
cout<<n-t-1<<endl;
}