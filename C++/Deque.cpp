#include<iostream>
#include<deque>
using namespace std;
int main()
{
    deque<long long> de;
    int q;
    cin>>q;
    for(int i=1;i<=q;i++){
        long long a,d,x,p;
        cin>>a;
        if(a==0){
            cin>>d>>x;
            if(d==0){
                de.push_front(x);
            }else if(d==1){
                de.push_back(x);
            }
        }else if(a==1){
            cin>>p;
            cout<<de[p]<<endl;
        }else if(a==2){
            cin>>d;
            if(d==0){
                de.pop_front();
            }else if(d==1){
                de.pop_back();
            }
        }
    }
}


