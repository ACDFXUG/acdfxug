#include <iostream>

struct TreeNode{
    int val;
    TreeNode *left,*right;
    TreeNode(int x=0,TreeNode *l=NULL,TreeNode *r=NULL):
    val(x),left(l),right(r){}
    ~TreeNode(){
        if(left) delete left;
        if(right) delete right;
    }
};

TreeNode* insertIntoBST(TreeNode* root, int val) {
    if(root==NULL) return root=new TreeNode(val);
    for(auto p=root;p;){
        if(val<p->val){
            if(p->left==NULL){
                p->left=new TreeNode(val);
                break;
            }
            p=p->left;
        }else{
            if(p->right==NULL){
                p->right=new TreeNode(val);
                break;
            }
            p=p->right;
        }
    }
    return root;
}