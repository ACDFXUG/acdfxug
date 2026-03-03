#include <iostream>
#include <unordered_set>
#include <vector>
#include <unordered_map>

template<class K,class V>
using hashmap=std::unordered_map<K,V>;

template<typename K>
concept Hashed=requires(const K &a,const K &b){
    // 检查是否有有效的哈希函数
    {std::hash<K>{}(a)}->std::convertible_to<std::size_t>;
    // 检查是否有相等性比较运算符
    {a==b}->std::convertible_to<bool>;
};

template<Hashed T>
class UnionFind{
public:
	hashmap<T,T> parent;//parent[x]表示x的父节点
    hashmap<T,size_t> sizz;//size[x]表示x所在集合的元素个数
    UnionFind()=default;
    T &Find(const T &x){
        if(!parent.contains(x)){
            parent[x]=x;
            sizz[x]=1;
        }
        if(x!=parent[x]){
            parent[x]=Find(parent[x]);
        }
        return parent[x];
    }
    T &operator [](const T &x){
        return Find(x);
    }
    size_t size(this UnionFind &self,const T &x){
        return self.sizz[self[x]];
    }
    void Union(this UnionFind &self,const T &x,const T &y){
        T rx = self[x];  // 取值，不是引用！
        T ry = self[y];
        if (rx == ry) return;
        
        // 提前缓存 size，避免后续 rx/ry 被修改影响
        size_t size_rx = self.sizz[rx];
        size_t size_ry = self.sizz[ry];
        
        if (size_rx < size_ry) {
            self.parent[rx] = ry;
            self.sizz[ry] = size_rx + size_ry;  // 直接赋值，不要 +=
        } else {
            self.parent[ry] = rx;
            self.sizz[rx] = size_rx + size_ry;
        }
    }
    void operator [](const T &x,const T &y){
        Union(x,y);
    }
    bool operator ()(this UnionFind &self,const T &x,const T &y){
        return self[x]==self[y];
    }
};

using namespace std;

struct Island{
    int x,y;
    Island(int x=0,int y=0):x(x),y(y){}

    Island(const Island &island)=default;
    Island(Island &&island)=default;

    Island &operator =(const Island &island){
        x=island.x;
        y=island.y;
        return *this;
    }

    Island &operator =(Island &&island){
        x=std::move(island.x);
        y=std::move(island.y);
        return *this;
    }

    bool operator ==(const Island &island) const{
        return x==island.x&&y==island.y;
    }

};

template<>
struct std::hash<Island>{
    size_t operator ()(const Island &island) const{
        return std::hash<int>{}(island.x)*31+std::hash<int>{}(island.y);
    }
};

int numIslands(vector<vector<char>>& grid) {
    UnionFind<Island> uf;
    for(int i=0;i<grid.size();i++){
        for(int j=0;j<grid[i].size();j++){
            if(grid[i][j]=='1'){
                Island island(i,j);
                uf[island];
                if(i>0&&grid[i-1][j]=='1'){
                    uf[island,Island(i-1,j)];
                }
                if(j>0&&grid[i][j-1]=='1'){
                    uf[island,Island(i,j-1)];
                }
            }
        }
    }
    int ans=0;
    for(const auto &[x,p]:uf.parent){
        if(uf[x]==x) ++ans;
    }
    return ans;
}

int main(){
    vector<vector<char>> grid={
        {'1','1','0','0','0'},
        {'1','1','0','0','0'},
        {'0','0','1','0','0'},
        {'0','0','0','1','1'}
    };
    cout<<numIslands(grid)<<endl;
    return 0;

}