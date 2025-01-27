#include <iostream>

struct ListNode{
    int val;
    ListNode *next;
    ListNode(int x=0):val(x),next(NULL){}
};

ListNode *detectCycle(ListNode *head) {
    auto fast=head,slow=head;
    while(fast!=NULL&&fast->next!=NULL){
        fast=fast->next->next;
        slow=slow->next;
        if(fast==slow){
            fast=head;
            while(fast!=slow){
                fast=fast->next;
                slow=slow->next;
            }
            return fast;
        }
    }
    return NULL;
}