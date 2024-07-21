#include <iostream>
using namespace std;
int MaxSum(int *a,int n,int sum,int b) {
	for (int i = 1; i <= n; i++) {
		if (b >= 0) {
			b += a[i];
		} else {
			b = a[i];
		}
		if (b > sum) {
			sum=b;
		}
	}
	return sum;
}

int main() {
	int a[200000],M,N;
    cin>>M;
	for(int i=1;i<=M;i++){
	cin >> N;
	for (int i = 1; i <= N; i++) {
		cin >> a[i];
	}
	cout << MaxSum(a,N,0,0) << endl; 
}
}