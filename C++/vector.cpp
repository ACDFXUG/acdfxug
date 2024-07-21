#include<iostream>
#include<vector>
using namespace std;
int main()
{
    vector<long long> A;
    long long q;
    cin>>q;
    for(int i=1;i<=q;i++){
        long long n,m;
        cin>>n;
        if(n==0){
            cin>>m;
            A.push_back(m);
        }else if(n==1){
            cin>>m;
            cout<<A[m]<<endl;
        }else if(n==2){
            A.pop_back();
        }
    }
}