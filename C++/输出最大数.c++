#include <iostream>
#include <algorithm>
using namespace std;
int main(){
    int a[3];
    cin>>a[0]>>a[1]>>a[2];
    sort(a,a+3,greater<int>());
    cout<<a[0]<<a[1]<<a[2];
}