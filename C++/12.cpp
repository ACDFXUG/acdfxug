#include <iostream>
#include <iomanip>
#include <cmath>
#include <stdlib.h>
using namespace std;
int main()
{
    int x,n;
    cin>>x>>n;
    while(x!=-1&&n!=-1&&n<=15){
        cout.setf(ios::fixed,ios::floatfield);
        cout.precision(0);
        cout<<pow(x,n)<<endl;
        cin>>x>>n;
    }
}