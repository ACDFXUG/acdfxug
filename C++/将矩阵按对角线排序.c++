#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

vector<vector<int>> diagonalSort(vector<vector<int>>& mat) {
    const int m=mat.size(),n=mat[0].size();
    vector<int> v;
    for(int i=0;i<m;i++){
        int x=i,y=0;
        while(x<m&&y<n){
            v.push_back(mat[x++][y++]);
        }
        sort(v.begin(),v.end());
        x=i,y=0;
        for(auto &s:v){
            mat[x++][y++]=s;
        }
        v.clear();
    }
    for(int i=1;i<n;i++){
        int x=0,y=i;
        while(x<m&&y<n){
            v.push_back(mat[x++][y++]);
        }
        sort(v.begin(),v.end());
        x=0,y=i;
        for(auto &s:v){
            mat[x++][y++]=s;
        }
        v.clear();
    }
    return mat;
}

int main(){
    vector<vector<int>> mat={
        {11,25,66,1,69,7},
        {23,55,17,45,15,52},
        {75,31,36,44,58,8},
        {22,27,33,25,68,4},
        {84,28,14,11,5,50}
    };
    vector<vector<int>> res=diagonalSort(mat);
    for(auto &v:res){
        for(auto &i:v){
            cout<<i<<" ";
        }
        cout<<endl;
    }
    return 0;
}