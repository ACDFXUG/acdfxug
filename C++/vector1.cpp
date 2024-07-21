#include<iostream>
#include<vector>
using namespace std;
int main()
{
    long long n,q;
    cin>>n>>q;
    vector<long long> v;
    vector<vector<long long> > V(n,v);
    for(int i=1;i<=q;i++){
        long long s,t,x;
        cin>>s;
        if(s==0){
            cin>>t>>x;
            V[t].push_back(x);
        }else if(s==1){
            cin>>t;
            for(int j=0;j<V[t].size();j++){
                cout<<V[t][j];
                if(j<V[t].size()-1){
                    cout<<" ";
                }
            }
            cout<<endl;
        }else if(s==2){
            cin>>t;
            V[t].clear();
        }
    }
}