#include <iostream>
#include <vector>
#include <algorithm>

class ListNode{
public:
    int val;
    ListNode *next;
    ListNode():val(0),next(nullptr){}
    ListNode(int x):val(x),next(nullptr){}
    ListNode(int x, ListNode *next):val(x),next(next){}
};

ListNode* sortList(ListNode* head) {
    if(head==nullptr){
        return nullptr;
    }
    std::vector<int> list;
    list.reserve(50000);
    for(ListNode *p=head;p!=nullptr;p=p->next){
        list.push_back(p->val);
    }
    std::sort(list.begin(),list.end(),std::greater<int>());
    ListNode *ans=new ListNode(list[0]);
    for(int i=1,l=list.size();i<l;i++){
        ans=new ListNode(list[i],ans);
    }
    return ans;
}

ListNode *init(std::initializer_list<int> l){
    ListNode *ans=new ListNode(*l.begin());
    for(auto i=l.begin()+1;i!=l.end();i++){
        ans=new ListNode(*i,ans);
    }
    return ans;
}

int main(){
    ListNode *head=init({-1,5,3,4,0});
    ListNode *ans=sortList(head);
    for(ListNode *p=ans;p!=nullptr;p=p->next){
        std::cout<<p->val<<" ";
    }
}