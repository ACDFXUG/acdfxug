#include <iostream>

struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x=0):val(x),next(nullptr){}
    ListNode(int x,ListNode *next):
    val(x),next(next){}
};

ListNode *mergeTwoLists(ListNode *list1,ListNode *list2) {
    if(list1==nullptr&&list2==nullptr) return nullptr;
    ListNode *head=new ListNode(),*tail=head;
    for(;list1||list2;){
        if(list1==nullptr&&list2!=nullptr){
            tail->val=list2->val;
            tail->next=list2->next;
            break;
        }else if(list2==nullptr&&list1!=nullptr){
            tail->val=list1->val;
            tail->next=list1->next;
            break;
        }else{
            if(list1->val<list2->val){
                tail->val=list1->val;
                list1=list1->next;
            }else{
                tail->val=list2->val;
                list2=list2->next;
            }
            tail=tail->next=new ListNode();
        }
    }
    return head;
}

int main(){
    ListNode *list1=new ListNode(1,new ListNode(2,new ListNode(4)));
    ListNode *list2=new ListNode(1,new ListNode(3,new ListNode(4)));
    ListNode *head=mergeTwoLists(list1,list2);
    for(;head;head=head->next){
        std::cout<<head->val<<" ";
    }
}