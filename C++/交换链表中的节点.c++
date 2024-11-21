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
    std::vector<int> vals;
    for (auto p=head; p; p=p->next) {
        vals.push_back(p->val);
    }
    std::swap(vals[k-1], vals[vals.size()-k]);
    
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