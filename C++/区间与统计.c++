#include <iostream>
using namespace std;
int main()
{
    int T,t=0,n,p;
    int *q=new int[n];
    cin>>T;
    for(int h=0;h<T;h++){
        cin>>n>>p;
        for(int i=0;i<n;i++){
            cin>>q[i];
        }
        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++){
                int sum=0;
                for(int s=i;s<=j;s++){
                    sum+=q[s];
                }
                if(sum==p){
                    t++;
                }
            }
        }
        cout<<t<<endl;
    }
}