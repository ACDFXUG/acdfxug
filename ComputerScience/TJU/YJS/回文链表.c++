#include <print>

struct ListNode{
    int val;
    ListNode *next;
    ListNode(int val=0,ListNode *next=nullptr):
    val(val),next(next){}
};

bool isPalindrome(ListNode* head) {
    auto fast=head,slow=head;
    while(fast&&fast->next){
        fast=fast->next->next;
        slow=slow->next;
    }
    ListNode *pre=nullptr,*cur=slow,*nxt=nullptr;
    while(cur){
        nxt=cur->next;
        cur->next=pre;
        pre=cur;
        cur=nxt;
    }
    while(pre){
        if(pre->val!=head->val) return false;
        pre=pre->next;
        head=head->next;
    }
    return true;
}