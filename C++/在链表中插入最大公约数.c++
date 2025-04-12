#include <iostream>

struct ListNode{
    int val;
    ListNode *next;
    ListNode(int x=0,ListNode *nxt=0):val(x),next(nxt){}
};
ListNode* insertGreatestCommonDivisors(ListNode* head) {
    if(head==nullptr) return nullptr;
    if(head->next==nullptr) return head;
    static auto gcd=[](this auto &self,int x,int y)->int {
        return y?self(y,x%y):x;
    };
    ListNode *tmp=new ListNode(head->val),*ans=tmp;
    for(auto p=head;p&&p->next;p=p->next){
        int g=gcd(p->val,p->next->val);
        tmp=tmp->next=new ListNode(g);
        tmp=tmp->next=new ListNode(p->next->val);
    }
    return ans;
}

int main(){
    ListNode *head=new ListNode(2,new ListNode(5,new ListNode(6,new ListNode(9,new ListNode(12))))),
        *ans=insertGreatestCommonDivisors(head);
    for(auto p=ans;p;p=p->next){
        std::cout<<p->val<<" ";
    }
}