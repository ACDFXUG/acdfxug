#include <iostream>

using namespace std;
int main()
{
    int i,n=0,sum=0,max,min; int a[n];
    cin>>i;
    
    for(n;n<i;n++){
        cin>>a[n];
    }

     max=a[0],min=a[0];
    for(n;n<i;n++){        
        if(max<a[n]) {max = a[n];}       
        if(min>a[n]) {min = a[n];}      
    }
    for(n;n<i;n++){
        sum=sum+a[n];
    }
    
    cout<<max<<" "<<min<<" "<<sum;
    return 0;
}