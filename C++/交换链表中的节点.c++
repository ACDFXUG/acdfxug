#include <iostream>
#include <algorithm>
#include <vector>

struct ListNode {
	int val;
	ListNode *next;
	ListNode(int x=0,ListNode *next=nullptr):
    val(x),next(next){}
};

ListNode* swapNodes(ListNode* head, int k) {
    auto p1=head,p2=head,p3=head;
    for(int i=1;i<k;i++){
        p1=p1->next;
    }
    while(p1->next){
        p1=p1->next;
        p2=p2->next;
    }
    for(int i=1;i<k;i++){
        p3=p3->next;
    }
    std::swap(p2->val,p3->val);
    return head;
}

int main() {
    ListNode *head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
    auto p = swapNodes(head, 2);
    while (p) {
        std::cout << p->val << " ";
        p = p->next;
    }
    return 0;
}