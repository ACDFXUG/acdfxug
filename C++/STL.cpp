#include<iostream>
#include<vector>
using namespace std;
int main()
{
    vector<int> V;
    for(int i=1;i<=10;i++){
        V.push_back(i);
    }
        V.erase(V.begin()+5);
        V.erase(V.begin(),V.begin()+3);
        V.pop_back();
        for(int i=0;i<V.size();i++){
            cout<<V[i]<<endl;
        }
    
    
}