#include <iostream>
#include <queue>
#include <stack>
using namespace std;
int main()
{
    
    int m,n,k;
    string st;
    cin>>m;
    for(int a=0;a<m;a++){
        cin>>n;
        queue<int> q;
            stack<int> s;
        for(int i=0;i<n;i++){
            cin>>st;           
            if(st=="push"){
                cin>>k;
                q.push(k);
                s.push(k);
            }else if(st=="pop"){
                if(q.size()==0){
                    cout<<"error"<<endl;
                }else{
                    q.pop();
                }
                if(s.size()==0){
                    cout<<"error"<<endl;
                }else{
                    s.pop();
                }
            }
        }
        int s1=q.size(),s2=s.size();
        int *w=new int[s2];
        for(int j=0;j<s1;j++){
            cout<<q.front()<<" ";
            q.push(q.front());
            q.pop();
        }
        cout<<endl;
        for(int j=s2-1;j>=0;j--){
            w[j]=s.top();
            s.pop();
        }
        for(int j=0;j<s2;j++){
            cout<<w[j]<<" ";
        }
        cout<<endl;
    }
}