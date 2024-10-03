#include <iostream>
#include <vector>
#include <algorithm>

struct ListNode{
    int val;
    ListNode *next;
    ListNode():val(0),next(nullptr){}
    ListNode(int x):
    val(x),next(nullptr){}
    ListNode(int x,ListNode *next):
    val(x),next(next){}
};

std::vector<int> trans(ListNode *head){
    std::vector<int> res;
    for(auto p=head;p;p=p->next){
        res.push_back(p->val);
    }
    return res;
}

ListNode *mergeKLists(std::vector<ListNode*> &lists) {
    if(lists.empty()){
        return nullptr;
    }
    std::vector<int> ans;
    for(auto p:lists){
        auto v=trans(p);
        ans.insert(ans.end(),v.begin(),v.end());
    }
    if(ans.empty()){
        return nullptr;
    }
    std::sort(ans.begin(),ans.end());
    auto head=new ListNode(ans[0]),p=head;
    for(int i=1;i<ans.size();i++){
        p=p->next=new ListNode(ans[i]);
    }
    return head;
}
