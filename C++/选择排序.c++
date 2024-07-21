#include<iostream>
#include<algorithm>
using namespace std;
int main()
{
    int m;
    cin>>m;
    for(int i=0;i<m;i++){
        int n;
        cin>>n;
        int *p=new int[n];
        for(int j=0;j<n;j++){
            cin>>p[j];
        }
        sort(p,p+n,less<int>());
        for(int s=0;s<n;s++){
            cout<<p[s]<<" ";
        }
        cout<<endl;
    }
}