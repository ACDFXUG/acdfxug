#include <iostream>

template<class T> class ListNode{
public:
    T val;
    ListNode *next;
    ListNode *prev;
    ListNode():
    val(T()),next(nullptr),prev(nullptr){}
    ListNode(T val):
    val(val),next(nullptr),prev(nullptr){}
    ListNode(T val,ListNode *next):
    val(val),next(next){}
    ListNode(ListNode *prev,T val):
    val(val),prev(prev){}
    ~ListNode(){
        delete next,prev;
    }
};

int main(){
    ListNode<int> *head=new ListNode(1),*tail=head;
    for(int i=2;i<=9;i++){
        tail->next=new ListNode(i);
        tail->next->prev=tail;
        tail=tail->next;
    }
    for(auto *p=tail;p!=nullptr;p=p->prev){
        std::cout<<p->val<<" ";
    }

}