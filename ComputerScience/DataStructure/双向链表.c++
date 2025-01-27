
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
 
using namespace std;
 
typedef int ElemType;
/*双向链表的结点,包含一个数据域，两个指针域*/
typedef struct DoubleNode{
    ElemType data;
    struct DoubleNode * prev; // 指向前缀结点
    struct DoubleNode * next; //指向后继结点
}DoubleNode,* DoubleLinkList;
 
 
DoubleLinkList InitDoubleLinkList()
{
    DoubleLinkList dlLink;
    dlLink = (DoubleLinkList)malloc(sizeof(DoubleNode));
    if(dlLink == NULL)
        cout << "申请空间失败！";
    dlLink->next = NULL;
    return dlLink;
}
void OutPut(DoubleLinkList dlLink)
{
    DoubleLinkList temp = dlLink;
    
    while(temp->next != NULL)
    {
        temp = temp->next;
        cout << temp->data << " ";
    }
    cout << endl;
}
/*void CreatDoubleLinkListHead(DoubleLinkList &dlLink,int length)
{
    DoubleLinkList node = NULL;
    for(int i = 0;i <length;i++)
    {
        node = (DoubleLinkList)malloc(sizeof(DoubleNode));
        cout << "输入第" << i+1 <<"个元素：";
        ElemType x;
        cin >> x;
        node->data = x;
 
        node->next = dlLink->next;
        dlLink->next->prev = node;
        node->prev = dlLink;
        dlLink->next = node;
    }
}*/
 
void CreatDoubleLinkListTail(DoubleLinkList &head,int length)
{
    DoubleLinkList node = NULL;
    DoubleLinkList tail = head;
    for(int i = 0;i < length;i++)
    {
        node = (DoubleLinkList)malloc(sizeof(DoubleNode));
        ElemType x;
        cin >> x;
        node->data = x;
 
        tail->next = node;
        node ->prev = tail;
        tail = node;
 
    }
    tail ->next = NULL;
}
void InsertNode(DoubleLinkList &dlLink,int pos,ElemType element)
{
    DoubleLinkList node = (DoubleLinkList)malloc(sizeof(DoubleNode));
    DoubleLinkList p = dlLink;
    int j = 1;
    while(j < pos)
    {
        p = p->next;
        j++;
    }
    //此时的p为前驱结点
    node->data = element;
 
    node->next = p->next;
    p->next->prev = node;
    p->next = node;
    node->prev = p;
}
void DeleteNode(DoubleLinkList &dlLink,int pos)
{
    DoubleLinkList p = dlLink;
    DoubleLinkList deleteNode;
    int j = 1;
    while(j < pos)
    {
        p = p->next;
        j++;
    }
     //此时的p为前驱结点
     deleteNode = p->next;
     deleteNode->next ->prev = p;
     p->next = deleteNode->next;
     free(deleteNode);
 
 
}
 
int main()
{
    DoubleLinkList dlLink = InitDoubleLinkList();
    int length;
    cin >> length;
   // CreatDoubleLinkListHead(dlLink,length);
   CreatDoubleLinkListTail(dlLink,length);
    int element;
    cin >> element;
    int pos;
    cin >> pos;
    InsertNode(dlLink,pos,element);
    OutPut(dlLink);
    cin >> pos;
    DeleteNode(dlLink,pos);
    OutPut(dlLink);
    return 0;
}