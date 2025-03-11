#include <vector>
#include <iostream>

struct ListNode{
    int val;
    ListNode *next;
    ListNode(int x=0,ListNode *next=NULL):
    val(x),next(next){}
};

void reorderList(ListNode* head) {
    std::vector<ListNode *> list_nodes;
    for(auto p=head;p;p=p->next){
        list_nodes.push_back(p);
    }
    auto p=head;
    for(int i=1,j=list_nodes.size()-1;i<=j;++i,--j){
        p=p->next=list_nodes[j];
        p=p->next=list_nodes[i];
    }
    p->next=nullptr;
}

int main(){
    ListNode *head=new ListNode(1);
    head->next=new ListNode(2);
    head->next->next=new ListNode(3);
    head->next->next->next=new ListNode(4);
    head->next->next->next->next=new ListNode(5);
    reorderList(head);
    for(auto p=head;p;p=p->next){
        std::cout<<p->val<<" ";
    }
}