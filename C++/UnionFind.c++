#include <iostream>
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
private:
	hashmap<T,T> parent;//parent[x]表示x的父节点
    hashmap<T,size_t> sizz;//size[x]表示x所在集合的元素个数
public:
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
        T &rx=self[x];
        T &ry=self[y];
        if(rx==ry) return;
        if(self.sizz[rx]<self.sizz[ry]){
            self.parent[rx]=ry;
            self.sizz[ry]+=self.sizz[rx];
        }else{
            self.parent[ry]=rx;
            self.sizz[rx]+=self.sizz[ry];
        }
    }
    void operator [](const T &x,const T &y){
        Union(x,y);
    }
    bool operator ()(this UnionFind &self,const T &x,const T &y){
        return self[x]==self[y];
    }
};

int main(){
    UnionFind<int> uf;
    int n,m,p;
    std::cin>>n>>m>>p;
    for(int i=0,x,y;i<m;i++){
        std::cin>>x>>y;
        uf[x,y];
    }
    for(int i=0,x,y;i<p;i++){
        std::cin>>x>>y;
        std::cout<<(uf(x,y)?"Yes\n":"No\n");
    }
}
