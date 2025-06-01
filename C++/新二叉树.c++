#include <iostream>
#include <unordered_map>

template<class K,class V>
using hashmap=std::unordered_map<K,V>;

template<class T>
struct TreeNode{
    T val;
    TreeNode *left,*right;
    TreeNode():val(0),left(nullptr),right(nullptr){}
    TreeNode(T val,TreeNode *left=nullptr,TreeNode *right=nullptr):
    val(val),left(left),right(right){}
};

std::string pre_order(TreeNode<char> *root){
    if(root==nullptr) return "";
    std::string res="";
    res+=root->val;
    res+=pre_order(root->left);
    res+=pre_order(root->right);
    return res;
}

int main(){
    int L;
    std::cin>>L;
    TreeNode<char> *root=nullptr;
    hashmap<char,TreeNode<char> *> tree;
    for(int i=0;i<L;i++){
        char a,b,c;
        std::cin>>a>>b>>c;
        if(!tree.contains(a))
            tree[a]=new TreeNode(a);
        tree[b]=tree[a]->left=b!='*'?new TreeNode(b):nullptr;
        tree[c]=tree[a]->right=c!='*'?new TreeNode(c):nullptr;
        if(i==0) root=tree[a];
    }
    std::cout<<pre_order(root)<<std::endl;
}