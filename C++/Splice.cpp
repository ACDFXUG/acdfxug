#include<iostream>
#include<list>
#include<vector>
using namespace std;
int main()
{
    list<long long> L;
    int n;
    cin>>n;
    vector<list<long long> > v(n,L);
    int q;
    cin>>q;
    for(int i=0;i<q;i++){
        int m,t,x;
        cin>>m;
        if(m==0){
            cin>>t>>x;
            v[t].push_back(x);
        }else if(m==1){
            cin>>t;
            for(list<long long>::iterator it=v[t].begin();it!=v[t].end();it++){
                cout<<*it<<" ";
            }
            cout<<endl;
        }else if(m==2){
            int s;
            cin>>s>>t;
            list<long long>::iterator it=v[t].end();
            v[t].splice(it,v[s]);
        }        
    }
}