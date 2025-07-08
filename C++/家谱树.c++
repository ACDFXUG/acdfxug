#include <iostream>
#include <unordered_map>
#include <unordered_set>

template<class K,class V>
using hashmap=std::unordered_map<K,V>;

template<class T>
using hashset=std::unordered_set<T>;

struct FamilyTree{
    hashmap<int,hashset<int>> tree;
    FamilyTree(int n):tree(n){}
    void add_edge(int parent,int son){
        tree[parent].insert(son);
    }
    void delete_vertex(int member){
        for(auto &[_,son]:tree){
            son.erase(member);
        }
        tree.erase(member);
    }
};

int find_0_indegree(const FamilyTree &tree){
    for(auto &[parent,son]:tree.tree){
        if(son.empty()){
            return parent;
        }
    }
    return -1;
}

int main(){
    int N;
    std::cin>>N;
    FamilyTree tree(N);
    for(int prt=1,son;prt<=N;++prt){
        while(std::cin>>son,son!=0){
            tree.add_edge(son,prt);
        }
        if(!tree.tree.contains(prt)) tree.tree[prt]={};
    }
    for(int i=1;i<=N;++i){
        int root=find_0_indegree(tree);
        if(root==-1)[[unlikely]]{
            std::cout<<"0\n";
            return 0;
        }
        std::cout<<root<<(i==N?'\n':' ');
        tree.delete_vertex(root);
    }
}