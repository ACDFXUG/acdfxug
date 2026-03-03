#include <print>
#include <vector>
#include <queue>

using namespace std;

struct TreeNode{
    int val;
    TreeNode *left,*right;
    TreeNode(int x=0,TreeNode *l=nullptr,TreeNode *r=nullptr):
    val(x),left(l),right(r){}
};

vector<int> rightSideView(TreeNode* root) {
    if(root==nullptr) return {};
    vector<int> res;
    queue<TreeNode *> bfs;
    bfs.emplace(root);
    int val;
    while(!bfs.empty()){
        int size=bfs.size();
        while(size-->0){
            auto node=bfs.front();
            bfs.pop();
            val=node->val;
            if(node->left) bfs.emplace(node->left);
            if(node->right) bfs.emplace(node->right);
        }
        res.push_back(val);
    }
    return res;
}

int main(){
    TreeNode *root=new TreeNode(1);
    root->left=new TreeNode(2);
    root->right=new TreeNode(3);
    root->left->right=new TreeNode(5);
    root->right->right=new TreeNode(4);
    println("{}",rightSideView(root));
}