#include <iostream>
#include <iomanip>
#include <cmath>
#include <stdlib.h>
using namespace std;
int main()
{
    
    double r;
    int n;
    
    cin>>n>>r;
    for(int i=1;i<=n;i++){
        
        cout<<fixed<<setprecision(2)<<abs(r)<<endl;
        cin>>r;
    }
}