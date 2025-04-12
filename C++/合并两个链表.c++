#include <iostream>
#include <vector>

struct ListNode{
    int val;
    ListNode *next;
    ListNode(int x=0,ListNode *next=nullptr):val(x),next(next){}
};


ListNode* mergeInBetween(ListNode* list1, int a, int b, ListNode* list2) {
    std::vector<ListNode *> list1vec;
    for(auto p=list1;p;p=p->next){
        list1vec.push_back(p);
    }
    list1vec[a-1]->next=list2;
    for(auto p=list2;p;p=p->next){
        if(p->next==nullptr){
            p->next=list1vec[b+1];
            break;
        }
    }
    return list1;
}