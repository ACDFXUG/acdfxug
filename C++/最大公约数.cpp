#include<iostream>
using namespace std;
long long f(long long n,long long m){
    long long s=1;
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
				long long k=0;
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
			long long l=m*s;	
			return l;	
		}
		return 0;				        			
}
int main()
{
    long long n,m;
    while(cin>>n>>m){
        if(n!=0&&m!=0){
            cout<<f(n,m)<<endl;
        }else{
            break;
        }
    }
}