#include <iostream>
#include <unordered_map>

struct ListNode{
    int id;
    ListNode *next,*prev;
    ListNode(int id=0,ListNode *next=nullptr,ListNode *prev=nullptr):
    id(id),next(next),prev(prev){}
    ListNode(ListNode &&s):id(s.id){
        this->next=s.next;
        this->prev=s.prev;
        if(s.next) delete s.next;
        if(s.prev) delete s.prev;
    }
    ListNode(const ListNode &s):
    ListNode(s.id,s.next,s.prev){}
    ListNode &operator =(ListNode &&s){
        id=s.id;
        next=s.next;
        prev=s.prev;
        if(s.next) delete s.next;
        if(s.prev) delete s.prev;
        return *this;
    }
    ListNode &operator =(const ListNode &s){
        id=s.id;
        next=s.next;
        prev=s.prev;
        return *this;
    }
};

int main(){
    std::unordered_map<int,ListNode *> students;
    students[1]=new ListNode(1);
    int N;
    scanf("%d",&N);
    for(int i=2,k,p;i<=N;i++){
        scanf("%d%d",&k,&p);
        auto &stu=students[i]=new ListNode(i);
        auto &stuk=students[k];
        if(p==0){//插到左边
            stu->next=stuk;
            stu->prev=stuk->prev;
            if(stuk->prev) stuk->prev->next=stu;
            stuk->prev=stu;
        }else{//插到右边
            stu->prev=stuk;
            stu->next=stuk->next;
            if(stuk->next) stuk->next->prev=stu;
            stuk->next=stu;
        }
    }
    int M;
    scanf("%d",&M);
    for(int x;M-->0;){
        scanf("%d",&x);
        if(students.contains(x)){//删除x
            auto &stux=students[x];
            if(stux->prev) stux->prev->next=stux->next;
            if(stux->next) stux->next->prev=stux->prev;
            delete stux;
            students.erase(x);
        }
    }
    auto head=students.begin()->second;
    while(head->prev) head=head->prev;
    for(;head;head=head->next){
        printf("%d ",head->id);
    }
}