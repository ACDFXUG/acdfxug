#include <iostream>
#include <vector>
#include <list>

struct TreeNode{
    int val;
    TreeNode *left,*right;
    TreeNode():val(0),left(nullptr),right(nullptr){}
    TreeNode(int x):
    val(x),left(nullptr),right(nullptr){}
    TreeNode(int x,TreeNode *left,TreeNode *right):
    val(x),left(left),right(right){}
};

std::list<int> pre_ordered(TreeNode *root){
    std::list<int> ans;
    if(root==nullptr) return ans;
    else{
        ans.push_back(root->val);
        auto l=pre_ordered(root->left),
        r=pre_ordered(root->right);
        ans.insert(ans.end(),l.begin(),l.end());
        ans.insert(ans.end(),r.begin(),r.end());
    }
    return ans;
}

void flatten(TreeNode* root) {
    if(root==nullptr) return;
    auto pre=pre_ordered(root);
    pre.pop_front();
    TreeNode *tmp=root;
    for(auto it=pre.begin();it!=pre.end();++it){
        tmp->left=nullptr;
        tmp=tmp->right=new TreeNode(*it);
    }
}