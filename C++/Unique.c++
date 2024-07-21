#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;
int main()
{
    long long n,a;
    cin>>n;
    vector<long long> v;
    for(int i=0;i<n;i++){
        cin>>a;
        v.push_back(a);
    }
    unique(v.begin(),v.end());
    int s=unique(v.begin(),v.end())-v.begin();
    for(int i=0;i<s;i++){
        cout<<v[i];
        if(i<s-1){
            cout<<" ";
        }
    }
}