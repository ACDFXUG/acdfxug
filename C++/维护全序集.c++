#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int minmax(vector<int> v,int x){
    sort(v.begin(),v.end());
    for(int i=0;i<v.size();i++){
        if(v[i]<x&&v[i+1]>x){
            return v[i];
        }
    }
    return -1;
}
int maxmin(vector<int> v,int x){
    sort(v.begin(),v.end());
    for(int i=0;i<v.size();i++){
        if(v[i]<x&&v[i+1]>x){
            return v[i+1];
        }
    }
    return -1;
}
int main(){
    vector<int> v;
    int n;
    cin>>n;
    for(int p,x;n>0;n--){
        cin>>p>>x;
        if(p==0){
            v.push_back(x);
        }else if(p==1){
            for(int i=0;i<v.size();i++){
                if(v[i]==x){
                    v.erase(v.begin()+i);
                }
                break;
            }
        }else if(p==2){
            sort(v.begin(),v.end());
            cout<<v[x-1]<<endl;
        }else if(p==3){
            int t=0;
            for(int s:v){
                if(s<x){
                    t++;
                }
            }
            cout<<t<<endl;
        }else if(p==4){
            cout<<minmax(v,x)<<endl;
        }else if(p==5){
            cout<<maxmin(v,x)<<endl;
        }
    }
}