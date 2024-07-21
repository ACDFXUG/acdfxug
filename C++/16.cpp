#include<iostream>
#include <iomanip>
#include <cmath>
#include <stdlib.h>
using namespace std;
void swap(char &a,char &b)
{
char t;
if(a>b)
{
t=a;
a=b;
b=t;
}
}
int main()
{
char a,b,c;
cin>>a>>b>>c;
while(a!='0'||b!='0'||c!='0'){
swap(a,b);
swap(a,c);
swap(b,c);
cout<<a<<" "<<b<<" "<<c<<endl;
cin>>a>>b>>c;
}
return 0;
}
