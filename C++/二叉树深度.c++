#include <iostream>
#include <stack>
#include <unordered_map>

struct TreeNode{
    int id;
    TreeNode *left,*right;
    TreeNode(int id,
        TreeNode *left=nullptr,
        TreeNode *right=nullptr
    ):id(id),left(left),right(right){}
    int get_depth(){
        int max_depth=0;
        std::stack<std::pair<TreeNode *,int>> tree;
        tree.emplace(this,1);
        while(!tree.empty()){
            const auto [node,depth]=tree.top();
            //  const auto &[node,depth]=tree.top();
            //  是错误的,引用会导致pop后的node失效
            //  从而导致程序提前退出
            tree.pop();
            max_depth=std::max(max_depth,depth);
            if(node->right){
                tree.emplace(node->right,depth+1);
            }
            if(node->left){
                tree.emplace(node->left,depth+1);
            }
        }
        return max_depth;
    }
    ~TreeNode(){
        if(left) delete left;
        if(right) delete right;
    }
};

int main(){
    std::ios::sync_with_stdio(false);
    std::cin.tie(nullptr);
    std::cout.tie(nullptr);

    int node_num;
    std::cin>>node_num;
    TreeNode *root=new TreeNode(1);
    std::unordered_map<int,TreeNode *> tree;
    tree.emplace(1,root);
    for(int i=1,l,r;i<=node_num;++i){
        std::cin>>l>>r;
        TreeNode *cur;
        if(tree.contains(i)) cur=tree[i];
        else{
            cur=new TreeNode(i);
            tree.emplace(i,cur);
        }
        if(l!=0){
            if(tree.contains(l)){
                cur->left=tree[l];
            }else{
                cur->left=tree[l]=new TreeNode(l);
            }
        }
        if(r!=0){
            if(tree.contains(r)){
                cur->right=tree[r];
            }else{
                cur->right=tree[r]=new TreeNode(r);
            }
        }
    }
    std::cout<<root->get_depth()<<'\n';
}