#include <iostream>
#include <vector>

struct ListNode{
    int val;
    ListNode *next;
    ListNode():val(0),next(nullptr){}
    ListNode(int x):
    val(x),next(nullptr){}
    ListNode(int x,ListNode *next):
    val(x),next(next){}
};

ListNode* removeNthFromEnd(ListNode* head, int n) {
    std::vector<int> v;
    for(ListNode *p=head;p;p=p->next){
        v.push_back(p->val);
    }
    v.erase(v.end()-n);
    if(v.empty()){
        return nullptr;
    }
    ListNode *ans=new ListNode(*v.rbegin());
    for(auto it=v.rbegin()+1;it<v.rend();it++){
       ans=new ListNode(*it,ans);
    }
    return ans;
}