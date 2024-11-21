#include <iostream>

struct ListNode{
    int val;
    ListNode *next;
    ListNode(int x=0,ListNode *next=nullptr):
    val(x),next(next){}
};

ListNode* oddEvenList(ListNode* head) {
    if(head==nullptr){
        return nullptr;
    }
    ListNode *odd=head,*even=head->next,*evenHead=even;
    while(even!=nullptr&&even->next!=nullptr){
        odd=odd->next=even->next;
        even=even->next=odd->next;
    }
    odd->next=evenHead;
    return head;
}

int main(){
    auto p=nullptr;
}