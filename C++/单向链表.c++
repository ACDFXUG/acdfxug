#include <iostream>
#include <unordered_map>

struct ListNode{
    const int val;
    ListNode *next;
    ListNode(int x=0,ListNode *next=nullptr):
    val(x),next(next){}
};

int main(){
    ListNode *head=new ListNode(1);
    std::unordered_map</*Node->val*/int,/*Node*/ListNode *> valNode{{1,head}};
    int q;
    scanf("%d",&q);
    for(;q-->0;){
        int op,x;
        scanf("%d%d",&op,&x);
        if(valNode.contains(x)){
            auto &node=valNode[x];
            switch(op){
                case 1:{
                    int y;
                    scanf("%d",&y);
                    valNode[y]=node->next=new ListNode(y,node->next);
                    break;
                }case 2:{
                    printf("%d\n",node->next?node->next->val:0);
                    break;
                }case 3:{
                    if(node->next){
                        auto &next=node->next->next;
                        valNode.erase(node->next->val);
                        node->next=next;
                    }
                    break;
                }
            }
        }
    }
}