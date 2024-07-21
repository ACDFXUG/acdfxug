#include <iostream>
using namespace std;
int main()
{
    long long a[100];
    a[1]=1;
    a[2]=1;
    for(int i=3;i<100;i++){
        a[i]=a[i-1]+a[i-2];
    }
    int N;
    cin>>N;
for(int i=1;i<=N;i++) {
	int Pi;
    cin>>Pi;
	cout<<a[Pi]<<endl;
}
}