#include <iostream>
using namespace std;
int main()
{
    int m,n;
    while(cin>>m>>n){
        int t=0;
        for(int i=m;i<=n;i++) {
	int a=i%10;
	int b=((i-a)/10)%10;
	int c=(i-a-10*b)/100;
	int p=a*a*a+b*b*b+c*c*c;
	if(p==i) {
	cout<<i<<" ";	
	}else{
		t++;
	}
}
if(t==n-m+1) {
	cout<<"no"<<endl;
}
    }
}