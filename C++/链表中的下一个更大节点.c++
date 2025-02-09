#include <iostream>
#include <vector>

struct ListNode{
    int val;
    ListNode *next;
    ListNode(int x=0,ListNode *next=nullptr):val(x),next(next){}
};

std::vector<int> nextLargerNodes(ListNode* head) {
    std::vector<int> head_vec;
    for(auto p=head;p;p=p->next){
        head_vec.push_back(p->val);
    }
    std::vector<int> res(head_vec.size());
    for(int i=0;i<head_vec.size();++i){
        for(int j=i+1;j<head_vec.size();++j){
            if(head_vec[j]>head_vec[i]){
                res[i]=head_vec[j];
                break;
            }
        }
    }
    return res;
}