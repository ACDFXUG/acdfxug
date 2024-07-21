#include<iostream>
using namespace std;

struct TreeNode {
    char val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(char x) : val(x), left(NULL), right(NULL) {}
};

TreeNode* buildTree(string s, int& i) {
    if (i>=s.size()||s[i]=='#') {
        i++;
        return NULL;
    }
    TreeNode* root = new TreeNode(s[i++]);
    root->left = buildTree(s, i);
    root->right = buildTree(s, i);
    return root;
}

void inorderTraversal(TreeNode* root) {
    if (root==NULL) {
        return;
    }
    inorderTraversal(root->left);
    if (root->val!='#') {
        cout <<root->val<<" ";
    }
    inorderTraversal(root->right);
}

int main() {
    string s;
    while (cin>>s) {
        int i = 0;
        TreeNode* root = buildTree(s, i);
        inorderTraversal(root);
        cout<<endl;
    }
}