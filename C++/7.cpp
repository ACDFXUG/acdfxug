#include <iostream>
#include <iomanip>
#include <cmath>
#include <stdlib.h>
using namespace std;
int main()
{
    double a,b,c;
    cin>>a>>b>>c;
    cout<<fixed<<setprecision(2)<<abs(a)+abs(b)+abs(c);
}