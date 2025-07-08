#include <iostream>
#include <unordered_map>
#include <unordered_set>
#include <deque>
#include <list>

template<class K,class V>
using hashmap=std::unordered_map<K,V>;

template<class T>
using hashset=std::unordered_set<T>;

struct TreeNode{
    int val;
    TreeNode *left,*right;
    TreeNode(int x,TreeNode *l=nullptr,TreeNode *r=nullptr):
    val(x),left(l),right(r){}
    int depth() const{
        int dp=0;
        std::deque<std::pair<const TreeNode *,int>> dfs;
        dfs.emplace_back(this,1);
        while(!dfs.empty()){
            auto [node,depth]=dfs.back();
            dfs.pop_back();
            dp=std::max(dp,depth);
            if(node->right) dfs.emplace_back(node->right,depth+1);
            if(node->left) dfs.emplace_back(node->left,depth+1);
        }
        return dp;
    }
    int width() const{
        int wd=0;
        std::deque<const TreeNode *> bfs;
        bfs.emplace_back(this);
        while(!bfs.empty()){
            int size=bfs.size();
            wd=std::max(wd,size);
            for(int i=0;i<size;i++){
                auto node=bfs.front();
                bfs.pop_front();
                if(node->left) bfs.push_back(node->left);
                if(node->right) bfs.push_back(node->right);
            }
        }
        return wd;
    }
    ~TreeNode(){
        if(left) delete left;
        if(right) delete right;
    }
};

struct Tree{
    hashmap<int,TreeNode *> nodes;
    hashmap<int,int> parent;
    Tree(int n){
        this->nodes[1]=new TreeNode(1);
        this->parent[1]=-1;
    }
    auto &get_node(int u) const{
        return nodes.at(u);
    }
    void add_edge(int u,int v){
        if(!nodes.contains(u)) nodes[u]=new TreeNode(u);
        if(!nodes[u]->left){
            if(!nodes.contains(v)) nodes[v]=new TreeNode(v);
            nodes[u]->left=nodes[v];
        }else if(!nodes[u]->right){
            if(!nodes.contains(v)) nodes[v]=new TreeNode(v);
            nodes[u]->right=nodes[v];
        }
        parent[v]=u;
    }
    int distance(int from,int to) const{
        if(from==to)[[unlikely]] return 0;
        hashset<int> f;
        int u=from;
        for(;u!=-1;u=parent.at(u)){
            f.insert(u);
        }
        u=to;
        int lca=-1;
        for(;u!=-1;u=parent.at(u)){
            if(f.contains(u)){
                lca=u;
                break;
            }
        }
        std::list<int> path1,path2;
        for(int u=from;u!=lca;u=parent.at(u)){
            path1.push_back(u);
        }
        path1.push_back(lca);
        for(int u=to;u!=lca;u=parent.at(u)){
            path2.push_front(u);
        }
        path1.splice(path1.end(),path2);
        int ans=0;
        for(auto it=path1.begin(),last=std::prev(path1.end());it!=last;++it){
            auto son=*it,prt=*std::next(it);
            if(parent.at(son)==prt){
                ans+=2;
            }else{
                ans+=1;
            }
        }
        return ans;
    }
};

int main(){
    int n;
    std::cin>>n;
    Tree t(n);
    for(int i=1,u,v;i<n;++i){
        std::cin>>u>>v;
        t.add_edge(u,v);
    }
    int x,y;
    std::cin>>x>>y;
    int depth=t.get_node(1)->depth();
    int width=t.get_node(1)->width();
    int dist=t.distance(x,y);
    printf("%d\n%d\n%d\n",depth,width,dist);
}