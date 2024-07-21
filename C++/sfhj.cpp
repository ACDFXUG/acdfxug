#include<iostream>
using namespace std;
long int tj(int n){
	if(n==1){
		return 1;
	}
	if(n==2){
		return 2;
	}
	if(n>2){
	return tj(n-1)+tj(n-2);
    } 
}
int main()
{
	int n;
	cin>>n; 
	while(n!=0){
		cout<<tj(n)<<endl;;
		cin>>n;
	}
}
