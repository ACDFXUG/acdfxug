#include <iostream>
using namespace std;
long long febo(int n)
{
    if(n==0||n==1){
        return 1;
    }else if(n>=2){
        return febo(n-1)+febo(n-2);
    }
    return 0;
}
int main()
{
    int n;
    cin>>n;
    cout<<febo(n)<<endl;
}