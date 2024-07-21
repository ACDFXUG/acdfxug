#include <iostream>
using namespace std;
int main()
{
    int n,k;
    cin>>n>>k;
    int *a=new int[2*n];
    for(int i=0;i<n;i++){
        cin>>a[i];
        a[i+n]=a[i];
    }
    int *b=new int[2*n-k-1];
    for(int i=0;i<2*n-k-1;i++){
        int sum=0;        
            for(int h=i;h<i+k+1;h++){
                sum+=a[h];
            }
        b[i]=sum;
    }
    int max=0;
    for(int i=0;i<2*n-k-1;i++){
        if(b[i]>=max){
            max=b[i];
        }
    }
    cout<<max;
}