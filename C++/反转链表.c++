#include <iostream>

class ListNode {
public:
    int val;
    ListNode *next;
    ListNode():val(0),next(nullptr){}
    ListNode(int x):val(x),next(nullptr){}
    ListNode(int x,ListNode *next):
    val(x),next(next){}
};

ListNode* reverseList(ListNode* head) {
    if(head==nullptr){
        return nullptr;
    }
    ListNode *ans=new ListNode(head->val);
    for(ListNode *p=head->next;p!=nullptr;p=p->next){
        ans=new ListNode(p->val,ans);
    }
    return ans;
}

int main(){

}