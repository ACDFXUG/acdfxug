
#include <iostream>
#include <iomanip>
#include <cmath>
#include <stdlib.h>
using namespace std;
int main()
{
    double r;
    cin>>r;
    double pi=acos(-1);
    double S=pi*r*r;
    double C=2*pi*r;
    if(r>0){
    cout<<fixed<<setprecision(6)<<S<<" "<<C;
    }
}