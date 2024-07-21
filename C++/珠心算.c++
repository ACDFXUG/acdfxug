#include<iostream>
using namespace std;

int main()
{      
    int n,t=0;
        cin>>n;
        int p[n];
        int q[n];
        for(int i=0;i<n;i++){
            cin>>p[i];
            q[i]=p[i];
        }        
            for(int i=0;i<n-1;i++){
                for(int j=i+1;j<n;j++){  
                    for(int s=0;s<n;s++){          
                    if(q[s]==p[i]+p[j]&&s!=i&&s!=j){
                    t++;
                    q[s]=1;
                }                
            }
        }
    }
        cout<<t<<endl;
}