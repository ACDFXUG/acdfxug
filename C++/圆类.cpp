#include <iostream>
#include <iomanip>
#include <cmath>
#include <stdlib.h>
using namespace std;
int main()
{
    int x,y,r,ca;
    cin>>x>>y>>r>>ca;
    double pi=acos(-1);
    for(int i=1;i<=ca;i++) {
	int n;
    cin>>n;
	if(n==1) {
		cout<<x<<" "<<y<<endl;
	}else if(n==2) {
		cout<<fixed<<setprecision(5)<<pi*r*r<<endl;
	}else if(n==3) {
		cout<<fixed<<setprecision(5)<<2*pi*r<<endl;
	}else if(n==4) {
		int p,q,r1;
        cin>>p>>q>>r1;
		double l=sqrt((x-p)*(x-p)+(y-q)*(y-q));
		if(r+r1==l||r-r1==l||r1-r==l) {
			cout<<"Yes"<<endl;
		}else {
			cout<<"No"<<endl;
	    }
    }
    }
	cout<<"你好";
	return 0;
}