#include <print>
#include <vector>
#include <unordered_map>

using namespace std;

void setZeroes(vector<vector<int>>& matrix) {
    unordered_map<int,vector<int>> zero_index;
    for(int i=0;i<matrix.size();i++){
        for(int j=0;j<matrix[i].size();j++){
            if(matrix[i][j]==0){
                zero_index[i].push_back(j);
            }
        }
    }
    for(const auto &[row,cols]:zero_index){
        for(int i=0;i<matrix[row].size();i++){
            matrix[row][i]=0;
        }
        for(int col:cols){
            for(int i=0;i<matrix.size();i++){
                matrix[i][col]=0;
            }
        }
    }
}