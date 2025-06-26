#include <iostream>
#include <unordered_map>
#include <unordered_set>
#include <deque>
#include <vector>
#include <set>

struct Literature{
    std::unordered_map<int,std::set<int>> refer;
    Literature(const int &n){
        this->refer.reserve(n);
    }
    void add_refer(const int &lit,const int &ref){
        refer[lit].insert(ref);
    }
    auto DFS(const int &lit){
        std::vector<int> ans;
        std::deque<int> dfs;
        dfs.push_back(lit);
        std::unordered_set<int> visited;
        while(!dfs.empty()){
            int liter=dfs.back();
            dfs.pop_back();
            if(!visited.contains(liter)){
                ans.push_back(liter);
                visited.insert(liter);
                auto &ref_lit=refer[liter];
                for(auto it=ref_lit.rbegin();it!=ref_lit.rend();++it){
                    dfs.push_back(*it);
                }
            }
        }
        return ans;
    }
    auto BFS(const int &lit){
        std::vector<int> ans;
        std::deque<int> bfs;
        bfs.push_back(lit);
        std::unordered_set<int> visited;
        while(!bfs.empty()){
            int liter=bfs.front();
            bfs.pop_front();
            if(!visited.contains(liter)){
                ans.push_back(liter);
                visited.insert(liter);
                for(const int &ref:refer[liter]){
                    bfs.push_back(ref);
                }
            }
        }
        return ans;
    }
};

template<class T>
void println(const std::vector<T> &vec){
    for(int i=0;i<vec.size();++i){
        std::cout<<vec[i]<<(i==vec.size()-1?'\n':' ');
    }
}

int main(){
    int n,m;
    std::cin>>n>>m;
    Literature liter(n);
    for(int i=0,lit,ref;i<m;++i){
        std::cin>>lit>>ref;
        liter.add_refer(lit,ref);
    }
    println(liter.DFS(1));
    println(liter.BFS(1));
}