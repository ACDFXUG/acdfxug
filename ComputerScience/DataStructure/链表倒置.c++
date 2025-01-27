#include <iostream>
#include <algorithm>
using namespace std;
 
typedef struct Node* Position;
typedef Position List;
struct Node {
    int Data;
    List Next;
};
 
List CreatList(){
    List P = (List)malloc(sizeof(struct Node));
    P->Data = 0;
    P->Next = NULL;
    return P;
}
 
void AddElement(List head,int X){
    List T = head;
    while (T->Next != NULL){
        T = T->Next;
    }
    List P = (List)malloc(sizeof(struct Node));
    P->Data = X;
    P->Next = NULL;
    T->Next = P;
    return ;
}
 
void ShowList(List head){
    List T = head;
    while (T->Next != NULL){
        T = T->Next;
        cout << T->Data <<" ";
    }
    return ;
}
 
// 完美的倒置，线性算法
// 在处理10个结点以上时每次访问两个结点，让后面的结点指向前一个
// 让头节点指向最后一个结点，让第一个结点指向NULL就OK了
// 如果需要处理3个以下的结点，在加入判断条件即可
void ReverseList(List head){
    List T = head;
    List L = T->Next;
    List M = L->Next;
    List R = M->Next;
    L->Next = NULL;
 
    while (R->Next != NULL){
        M->Next = L;
        L = M;
        M = R;
        R = R->Next;
    }
    R->Next = M;
    M->Next = L;
    T->Next = R;
    return ;
}
 
int main()
{
    List Head = CreatList();
    int m;
    do{ 
        cin>>m;
        AddElement(Head,m);
    }while(cin.get()!='\n');
    ReverseList(Head);
    ShowList(Head);
}