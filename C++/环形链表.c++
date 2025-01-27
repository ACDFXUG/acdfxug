#include <iostream>

struct ListNode{
    int val;
    ListNode *next;
    ListNode(int x=0):val(x),next(NULL){}
};

bool hasCycle(ListNode *head) {
    auto fast=head,slow=head;
    for(;fast!=NULL&&fast->next!=NULL;){
        fast=fast->next->next;
        slow=slow->next;
        if(fast==slow) return true;
    }
    return false;
}