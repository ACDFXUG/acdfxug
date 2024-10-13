#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

vector<int> getStrongest(vector<int>& arr, int k) {
    sort(arr.begin(),arr.end());
    int n,m=arr[((n=arr.size())-1)>>1];
    sort(arr.begin(),arr.end(),[&m](int &x,int &y){
        int abs1=abs(x-m),abs2=abs(y-m);
        return abs1==abs2?x>y:abs1>abs2;
    });
    return vector<int>(arr.begin(),arr.begin()+k);
}

int main(){
    vector<int> arr({6,7,11,7,6,8});
    vector<int> res=getStrongest(arr,5);
    for(int i:res){
        cout<<i<<" ";
    }
}
