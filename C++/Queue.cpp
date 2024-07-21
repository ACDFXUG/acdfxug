#include<iostream>
#include<queue>
#include<vector>
using namespace std;
int main()
{
    priority_queue<long long> Q;
    int n;
    cin>>n;
    vector<priority_queue<long long> > v(n,Q);
    int q;
    cin>>q;
    for(int i=0;i<q;i++){
        int m,t,x;
        cin>>m;
        if(m==0){
            cin>>t>>x;
            v[t].push(x);
        }else if(m==1){
            cin>>t;
            cout<<v[t].top()<<endl;
        }else if(m==2){
            cin>>t;
            v[t].pop();
        }
    }
}