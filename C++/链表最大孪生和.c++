#include <iostream>
#include <vector>

struct ListNode{
    int val;
    ListNode *next;
    ListNode(int x=0,ListNode *next=nullptr):
    val(x),next(next){}
};

int pairSum(ListNode* head) {
    std::vector<int> head_vec;
    for(auto p=head;p;p=p->next){
        head_vec.push_back(p->val);
    }
    int max=0,l=head_vec.size();
    for(int i=0,half=l>>1;i<half;i++){
        max=std::max(max,head_vec[i]+head_vec[l-i-1]);
    }
    return max;
}