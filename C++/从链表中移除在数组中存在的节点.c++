#include <iostream>
#include <vector>
using namespace std;

class ListNode{
public:
    int val;
    ListNode *next;
    ListNode():val(0),next(nullptr){}
    ListNode(int x):val(x),next(nullptr){}
    ListNode(int x,ListNode *next):val(x),next(next){}
    ~ListNode(){
        delete next;
    }
};

ListNode* modifiedList(vector<int>& nums, ListNode* head) {
    ListNode *p=head;
    for(int i=0;i<nums.size();i++){
        for(;p!=nullptr;p=p->next){
            if(p->val==nums[i]){
                if(p->next!=nullptr){
                    p->val=p->next->val;
                    p->next=p->next->next;
                }
            }
        }
        p=head;
    }
    return head;
}

int main(){
    vector<int> nums={1,2,3};
    ListNode *head=new ListNode(1),*tail=head;
    for(int i=2;i<=5;i++){
        tail=tail->next=new ListNode(i);
    }
    head=modifiedList(nums,head);
    for(auto p=head;p!=nullptr;p=p->next){
        cout<<p->val<<" ";
    }
}