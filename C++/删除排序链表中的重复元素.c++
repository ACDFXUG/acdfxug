#include <iostream>

class ListNode{
public:
    int val;
    ListNode *next;
    ListNode():val(0),next(nullptr){}
    ListNode(int val):
    val(val),next(nullptr){}
    ListNode(int val,ListNode *next):
    val(val),next(next){}
};

ListNode *deleteDuplicates(ListNode *head) {
    if(head==nullptr){
        return head;
    }
    int dup[250]{},i;
    for(ListNode *p=head;p;p=p->next){
        dup[(p->val)+100]++;
    }
    ListNode *ans=nullptr,*p=ans;
    for(i=0;i<250;i++){
        if(dup[i]==1){
            p=ans=new ListNode(i-100);
            break;
        }
    }
    for(int j=i+1;j<250;j++){
        if(dup[j]==1){
            p=p->next=new ListNode(j-100);
        }
    }
    return ans;
}