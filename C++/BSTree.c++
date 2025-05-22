#include <iostream>
#include <print>
#include <concepts>
#include <vector>
#include <ranges>

template<std::totally_ordered T>
class BSTree{
private:
    
    T val;
    BSTree *left,*right;
    static BSTree *find_min(BSTree *node){
        while(node->left!=nullptr) node=node->left;
        return node;
    }
    static BSTree *remove(BSTree *root,T value){
        if(root==nullptr) return nullptr;
        else if(value<root->val) root->left=remove(root->left,value);
        else if(value>root->val) root->right=remove(root->right,value);
        else{
            if(root->left==nullptr) return root->right;
            else if(root->right==nullptr) return root->left;
            root->val=find_min(root->right)->val;
            root->right=remove(root->right,root->val);
        }
        return root;
    }
public:
    BSTree(const T &val,BSTree *left=nullptr,BSTree *right=nullptr):
        val(val),left(left),right(right){}
    bool insert(const T &value){
        if(value==val) return false;
        else if(value<val){
            if(left==nullptr){
                left=new BSTree(value);
                return true;
            }
            else return left->insert(value);
        }else{
            if(right==nullptr){
                right=new BSTree(value);
                return true;
            }
            else return right->insert(value);
        }
    }
    bool remove(const T &value){
        remove(this,value);
        return true;
    }
    bool contains(const T &value){
        BSTree *root=this;
        while(root!=nullptr){
            if(value==root->val) return true;
            else if(value<root->val) root=root->left;
            else root=root->right;
        }
        return false;
    }
    template<Iterator It>
    static BSTree *build(It first,It last){
        BSTree *root=new BSTree(*(first++));
        for(auto it=first;it!=last;++it){
            root->insert(*it);
        }
        return root;
    }
    template<std::ranges::range R>
    static BSTree *build(const R &rng){
        BSTree *root=new BSTree(*std::ranges::begin(rng));
        for(auto it=std::ranges::begin(rng)+1;it!=std::ranges::end(rng);++it){
            root->insert(*it);
        }
        return root;
    }
};