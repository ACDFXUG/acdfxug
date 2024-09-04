#include <iostream>
#include <unordered_map>
#include <set>
#include <vector>
using namespace std;

vector<vector<int>> findWinners(vector<vector<int>>& matches) {
    set<int> player;
    unordered_map<int,int> loser_count;
    for(auto &mat:matches){
        player.insert(mat[0]);
        player.insert(mat[1]);
        loser_count[mat[1]]++;
    }
    vector<vector<int>> res(2);
    for(auto &plr:player){
        if(loser_count[plr]==0){
            res[0].push_back(plr);
        }else if(loser_count[plr]==1){
            res[1].push_back(plr);
        }
    }
    return res;
}

int main(){

}