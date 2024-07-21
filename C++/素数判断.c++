#include <iostream>
using namespace std;
int main()
{
    int x,y;
    while(cin>>x>>y){
        if(x==0&&y==0){
            break;
        }else{
            int n,i;
            for(n=x;n<=y;n++) {
			for(i=2;i<n*n+n+41;i++) {
				if(n*n+n+41%i==0) {
					break;
				}				
			}
			if(i!=n*n+n+41) {
				cout<<"Sorry";
				break;
			}
		}
            if(n==y+1) {
            cout<<"OK";
		}
        }
    }
}

