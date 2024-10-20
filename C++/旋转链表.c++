#include <iostream>

struct ListNode{
    int val;
    ListNode *next;
    ListNode():val(0),next(nullptr){}
    ListNode(int x):
    val(x),next(nullptr){}
    ListNode(int x,ListNode *next):
    val(x),next(next){}
};

ListNode *rotateRight(ListNode *head, int k) {
    if(head==nullptr) return nullptr;
    if(k==0) return head;
    int len=0;
    ListNode *cur=head;
    for(;cur;cur=cur->next,len++);
    int rot=k%len,remain=len-rot;
    auto left=new ListNode(head->val),p=left,tmp=head;
    for(int i=1;i<remain;i++){
        p=p->next=new ListNode((tmp=tmp->next)->val);
    }
    if(tmp->next==nullptr){
        return left;
    }
    auto right=tmp->next,r=right;
    for(;r->next;r=r->next);
    r->next=left;
    return right;
}

int main(){
    ListNode *head=new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5)))));
    ListNode *res=rotateRight(head,2);
    for(;res;res=res->next){
        std::cout<<res->val<<" ";
    }
}