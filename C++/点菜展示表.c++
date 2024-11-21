#include <iostream>
#include <unordered_map>
#include <set>
#include <map>
#include <vector>
using namespace std;

vector<vector<string>> displayTable(vector<vector<string>>& orders) {
    map<int,unordered_map<string,int>> odrs;
    set<string> foods;
    for(auto &order:orders){
        int &&table=stoi(order[1]);
        auto &food=order[2];
        odrs[table][food]++;
        foods.insert(food);
    }
    vector<vector<string>> ans;
    ans.push_back({"Table"});
    for(auto &food:foods){
        ans[0].push_back(food);
    }
    for(auto &[table,odr]:odrs){
        vector<string> row={to_string(table)};
        for(auto &food:foods){
            row.push_back(to_string(odr[food]));
        }
        ans.push_back(row);
    }
    return ans;
}

int main(){
    vector<vector<string>> orders={
        {"David","3","Ceviche"},
        {"Corina","10","Beef Burrito"},
        {"David","3","Fried Chicken"},
        {"Carla","5","Water"},
        {"Carla","5","Ceviche"},
        {"Rous","3","Ceviche"}
    };
    auto &&ans=displayTable(orders);
    for(auto &i:ans){
        for(auto &j:i){
            cout<<j<<" ";
        }
        cout<<endl;
    }
}