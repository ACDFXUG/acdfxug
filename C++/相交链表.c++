#include <iostream>

struct ListNode{
    int val;
    ListNode *next;
    ListNode(int x=0):val(x),next(NULL){}
};

ListNode *getIntersectionNode(ListNode *headA, ListNode *headB) {
    if(headA==NULL||headB==NULL) return NULL;
    auto pA=headA,pB=headB;
    while(pA!=pB){//双指针法(当pA和pB指向交点或者都到表尾时跳出循环)
        pA=pA?pA->next:headB;
        pB=pB?pB->next:headA;
    }
    return pA;
}