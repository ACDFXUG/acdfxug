#include <iostream>
#include <vector>

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x):val(x),left(NULL),right(NULL){}
};

void println(std::vector<int> vec){
    for(int i=0,last=vec.size()-1;i<=last;i++){
        printf(i==last?"%d\n":"%d ",vec[i]);
    }
}

std::vector<int> pre_order(TreeNode *root){
    std::vector<int> res;
    if(root==nullptr) return res;
    res.push_back(root->val);
    auto left=pre_order(root->left);
    res.insert(res.end(),left.begin(),left.end());
    auto right=pre_order(root->right);
    res.insert(res.end(),right.begin(),right.end());
    return res;
}

std::vector<int> in_order(TreeNode *root){
    std::vector<int> res;
    if(root==nullptr) return res;
    auto left=in_order(root->left);
    res.insert(res.end(),left.begin(),left.end());
    res.push_back(root->val);
    auto right=in_order(root->right);
    res.insert(res.end(),right.begin(),right.end());
    return res;
}

std::vector<int> post_order(TreeNode *root){
    std::vector<int> res;
    if(root==nullptr) return res;
    auto left=post_order(root->left);
    res.insert(res.end(),left.begin(),left.end());
    auto right=post_order(root->right);
    res.insert(res.end(),right.begin(),right.end());
    res.push_back(root->val);
    return res;
}

int main(){
    int n;
    scanf("%d",&n);
    std::vector<TreeNode *> tree(n+1,nullptr);
    TreeNode *root=new TreeNode(1);
    tree[1]=root;
    for(int l=1,left,right;l<=n;++l){
        scanf("%d%d",&left,&right);
        TreeNode *tn;
        if(tree[l]) tn=tree[l];
        else{
            tn=new TreeNode(l);
            tree[l]=tn;
        } 
        if(left){
            if(tree[left]) tn->left=tree[left];
            else{
                tn->left=new TreeNode(left);
                tree[left]=tn->left;
            }
        }
        if(right){
            if(tree[right]) tn->right=tree[right];
            else{
                tn->right=new TreeNode(right);
                tree[right]=tn->right;
            }
        }
    }
    println(pre_order(root));
    println(in_order(root));
    println(post_order(root));
}