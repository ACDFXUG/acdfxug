#include <iostream>>
using namespace std;
int main()
{
    int M,N,Q;
    cin>>M>>N>>Q;
    int s[1000][1000];
    for(int i=0;i<M;i++) {
	for(int j=0;j<N;j++) {
		cin>>s[i][j];
	}
    }
    for(int k=0;k<Q;k++) {
	int x1,y1,x2,y2;
    cin>>x1>>y1>>x2>>y2;
    int L=0;
    if(x1<=x2&&y1<=y2) {
    for(int f=x1;f<=x2;f++) {
    	for(int g=y1;g<=y2;g++) {
    		L+=s[f][g];
    	}
    }
    }else if(x1>=x2&&y1>=y2) {
    	for(int f=x2;f<=x1;f++) {
        	for(int g=y2;g<=y1;g++) {
        		L+=s[f][g];
        	}
        }
    }else if(x1>=x2&&y1<=y2) {
    	for(int f=x2;f<=x1;f++) {
        	for(int g=y1;g<=y2;g++) {
        		L+=s[f][g];
        	}
        }
    }else if(x1<=x2&&y1>=y2) {
    	for(int f=x1;f<=x2;f++) {
        	for(int g=y2;g<=y1;g++) {
        		L+=s[f][g];
        	}
        }
    }
    cout<<L<<endl;
    }
}