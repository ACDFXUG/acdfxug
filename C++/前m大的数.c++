#include <iostream>
#include <algorithm>
using namespace std;
int main()
{
    int N,M;
    while(cin>>N>>M){
    int *p=new int[N];
    int *q=new int[N*(N-1)/2];
    for(int i=0;i<N;i++){
        cin>>p[i];
    }
    int s=0;    
    for(int i=0;i<N;i++){
        for(int j=i+1;j<N;j++){
        q[s]=p[i]+p[j];
        s++;
        }
    }
    sort(q,q+N*(N-1)/2,greater<int>());
    for(int i=0;i<M;i++){
        cout<<q[i]<<" ";
    }
    cout<<endl;
    }

}