#include <iostream>
#include <vector>
#include <unordered_map>

class Node{
public:
    int val;
    Node *next;
    Node *random;
    Node(int _val):
    val(_val),next(NULL),random(NULL){}
};

Node* copyRandomList(Node* head) {
    if(head==nullptr) return nullptr;
    Node *p=head;
    std::vector<Node *> new_vec;
    std::unordered_map<Node *,int> origin;
    for(int len=0;p;p=p->next){
        new_vec.push_back(new Node(p->val));
        origin[p]=len++;
    }
    int i=0;
    for(p=head;p;p=p->next,i++){
        auto &rdm=p->random;
        if(rdm){
            new_vec[i]->random=new_vec[origin[rdm]];
        }
    }
    for(int i=0;i<new_vec.size()-1;++i){
        new_vec[i]->next=new_vec[i+1];
    }
    return new_vec[0];
}

int main(){

}