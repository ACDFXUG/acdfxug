#include <iostream>
#include <vector>
using namespace std;

void duplicateZeros(vector<int>& arr) {
    const int len=arr.size();
    vector<int> temp;
    for(int &X:arr){
        if(X!=0){
            temp.push_back(X);
        }else{
            temp.emplace_back(0,0);
        }
    }
    arr=vector(temp.begin(),temp.begin()+len);
}