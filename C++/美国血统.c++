#include <iostream>
#include <print>

template<class T>
concept printable=requires(const T &a){
    {std::print("{}",a)}->std::same_as<void>;
};

template<printable T>
struct TreeNode{
    T val;
    TreeNode *left,*right;
    TreeNode(T val={},TreeNode *left=nullptr,TreeNode *right=nullptr):
    val(val),left(left),right(right){}
};

TreeNode<char> *build_tree(std::string_view pre,std::string_view in){
    if(pre.length()==0) return nullptr;
    auto root=new TreeNode<char>(pre[0]);
    for(int i=0;i<in.length();++i){
        if(in[i]==pre[0]){
            root->left=build_tree(pre.substr(1,i),in.substr(0,i));
            root->right=build_tree(pre.substr(i+1),in.substr(i+1));
        }
    }
    return root;
}

auto post=[](this auto &self,TreeNode<char> *const root){
    if(root==nullptr) return;
    self(root->left);
    self(root->right);
    std::print("{}",root->val);
};

int main(){
    std::string pre,in;
    std::cin>>in>>pre;
    auto root=build_tree(pre,in);
    post(root);
    std::println();
}