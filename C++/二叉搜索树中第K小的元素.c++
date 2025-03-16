#include <iostream>
#include <list>

struct TreeNode{
    int val;
    TreeNode *left,*right;
    TreeNode(int val=0,TreeNode *left=nullptr,TreeNode *right=nullptr):
    val(val),left(left),right(right){}
};

int kthSmallest(TreeNode* root, int k) {
    if(root==nullptr) return -1;
    std::list<int> tree;
    auto inorder=[&tree](this auto &self,TreeNode *root){
        if(root==nullptr) return;
        self(root->left);
        tree.push_back(root->val);
        self(root->right);
    };
    inorder(root);
    for(auto it=tree.begin();it!=tree.end();++it){
        if(k==1) return *it;
        --k;
    }
    return -1;
}