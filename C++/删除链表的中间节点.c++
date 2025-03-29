#include <iostream>

struct ListNode{
    int val;
    ListNode *next;
    ListNode(int x=0,ListNode *next):val(x),next(next){}
};

ListNode* deleteMiddle(ListNode* head) {
    if(head==nullptr||head->next==nullptr) return nullptr;
    else if(head->next->next==nullptr){
        head->next=nullptr;
        return head;
    }
    auto fast=head,slow=head;
    for(;fast->next;slow=slow->next){
        if(fast->next->next)[[likely]]{
            fast=fast->next->next;
        }else{
            fast=fast->next;
        }
    }
    slow->next=slow->next->next;
    slow->val=slow->next->val;
    return head;
}