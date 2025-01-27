#include <iostream>
#include <vector>

struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x=0):val(x),next(nullptr){}
    ListNode(int x,ListNode *next):
    val(x),next(next){}
};

ListNode* reverseBetween(ListNode* head, int left, int right) {
    if(head==nullptr) return nullptr;
    std::vector<int> elem;
    for(auto *p=head;p;p=p->next){
        elem.push_back(p->val);
    }
    std::reverse(elem.begin()+(left-1),elem.begin()+right);
    ListNode *ans=new ListNode(elem[0]),*tail=ans;
    for(int i=1;i<elem.size();++i){
        tail->next=new ListNode(elem[i]);
        tail=tail->next;
    }
    return ans;
}