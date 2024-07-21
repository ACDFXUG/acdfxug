#include <iostream>
#include <cmath>
using namespace std;
int INT=1000000007;
int gcd(int n,int m) {
		int s=1;
		if(n%2!=0||m%2!=0) {
			while(n!=m) {
			if(n>m) {
				n=n-m;
			}else if(n<m) {
				m=m-n;
			}
			}			
			return m;
		}else if(n%2==0&&m%2==0) {
				int k=0;
				while(n%2==0&&m%2==0) {
					k++;
					n=n/2;
					m=m/2;
				}
				for(int i=1;i<=k;i++) {
					s*=2;
				}					
				while(n!=m) {
					if(n>m) {
						n=n-m;
					}else if(n<m) {
						m=m-n;
			}			
			}
			int l=m*s;	
			return l;	
		}
        return 0;				        	
}
long long F(int n){
    long long p=0;
    for(int i=1;i<=n;i++){
        for(int j=1;j<=i;j++){
            p+=((i/gcd(i,j))%INT*(j/gcd(i,j))%INT)%INT;
        }
    }
    return p;
}
int main(){
    int n;
    cin>>n;
    cout<<F(n)%INT;
}