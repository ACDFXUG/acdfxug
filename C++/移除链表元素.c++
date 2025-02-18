#include <iostream>

struct ListNode{
    int val;
    ListNode *next;
    ListNode(int &&x=0,ListNode *nxt=nullptr):
    val(x),next(nxt){}
};

ListNode* removeElements(ListNode* head, int val) {
    if(head==nullptr) return nullptr;
    for(auto p=head;p->next;){
        if(p->next->val==val){
            p->next=p->next->next;
        }else{
            p=p->next;
        }
    }
    return head->val==val?head->next:head;
}

int main(){
    
}