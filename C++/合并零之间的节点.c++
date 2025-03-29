#include <iostream>

struct ListNode{
    int val;
    ListNode *next;
    ListNode(int x=0,ListNode *next=nullptr):val(x),next(next){}
};

ListNode* mergeNodes(ListNode* head) {
    if(head==nullptr) return nullptr;
    int sum=0;
    auto null=new ListNode(),t=null;
    for(auto p=head->next;p;p=p->next){
        if(p->val==0){
            auto tmp=new ListNode(sum);
            sum=0;
            t=t->next=tmp;
        }else[[likely]]{
            sum+=p->val;
        }
    } 
    return null->next;
}