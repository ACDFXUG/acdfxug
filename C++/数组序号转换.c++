#include <iostream>
#include <vector>
#include <algorithm>
#include <unordered_map>
using namespace std;

vector<int> arrayRankTransform(vector<int>& arr) {
    auto tmp=arr;
    sort(tmp.begin(),tmp.end());
    unordered_map<int,int> index;
    for(int i=1;const int &x:tmp){
        if(index.contains(x)){
            index[x]=i-1;
        }else{
            index[x]=i++;
        }
    }
    for(int &x:arr){
        x=index[x];
    }
    return arr;
}

int main(){
    
}