#include <iostream>

struct ListNode{
    int val;
    ListNode *next;
    ListNode(int x=0):val(x),next(nullptr){}
};

void deleteNode(ListNode* node) {
    for(;node->next->next;node=node->next){
        node->val=node->next->val;
    }
    node->val=node->next->val;
    node->next=nullptr;
}