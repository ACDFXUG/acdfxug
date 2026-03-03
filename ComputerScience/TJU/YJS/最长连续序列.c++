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

int longestConsecutive(vector<int>& nums) {
    unordered_set<int> number;
    for(const int &i:nums) number.insert(i);
    UnionFind<int> uf;
    for(const int &i:nums) uf[i];
    for(const int &i:nums){
        if(number.contains(i-1)) uf[i-1,i];
        if(number.contains(i+1)) uf[i,i+1];
    }
    int max=0;
    for(auto it=uf.sizz.begin();it!=uf.sizz.end();++it){
        max=max>it->second?max:it->second;
    }
    return max;
}

int main(){
    vector<int> nums={0,3,7,2,5,8,4,6,0,1};
    cout<<longestConsecutive(nums);
}