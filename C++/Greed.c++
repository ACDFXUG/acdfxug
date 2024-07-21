#include<iostream>
#include<algorithm>
using namespace std;
int main()
{
    int n;
    cin>>n;
    int *a=new int[n];
    int *b=new int[n];
    int sum=0;
    for(int i=0;i<n;i++){
        cin>>a[i];
        sum+=a[i];
    }
    for(int i=0;i<n;i++){
        cin>>b[i];
    }
    sort(b,b+n,greater<int>());
    int p=b[0]+b[1];
    if(p>=sum){
        cout<<"YES";
    }else{
        cout<<"NO";
    }
}