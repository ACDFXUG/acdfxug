#include <iostream>
#include <algorithm>
using namespace std;
typedef int ElemType;
typedef int Status;
typedef struct LNode
{
    ElemType data;
    struct LNode *next;
}LNode,*LinkList;


Status InitList(LinkList L)
{
    L = (LNode *)malloc(sizeof(LinkList));
    if(L == NULL)
    {
        cout<<"申请内存空间失败";
    }
    L->next = NULL;
    return 1;

}
//头插法建立单链表
//1.为头结点L申请空间
//2.创建指向新结点的指针p
//3.输入结点的值，为结点申请存储空间
//4.数据赋值给p->data
//5.进行插入新结点的操作
//6.即让p的next指针域指向头结点的后继；头结点的next指针域指向p


Status CreateList(LNode *head)
{

    int x;
    cout<<"请输入数值:";
    cin>>x;
    while(x != 9999)
    {

        LNode *node = (LNode *) malloc(sizeof(LNode));
        node->data = x;
        node->next = head->next;
        head->next = node;
        cin>>x;
    }
    return 1;

}

//尾插法建立单链表
//思路：设置尾指针，每次在尾指针后面插入新的结点，
//尾指针的next指针域指向新的结点
//当前新节点定义为尾结点
//尾指针置空

Status CreateListTail(LNode *head)
{
    int x;
     LNode *t = head;
    cin>>x;
    while(x != 9999)
    {
         LNode *node = (LNode *) malloc(sizeof(LNode));
         node->data = x;
         t->next = node;
         t = node;
        cin>>x;

    }
    t->next = NULL;
    return 1;

}

//利用位置获取元素位置
//所需条件：链表整表，位置i，以及用来接收i的指针e

Status GetElem(LinkList L,int i,ElemType *e)
{
    int j = 1; //计数器
    LNode *p = L->next;
    while(p != NULL && j<i)
    {
        p = p->next;
        j++;
    }
    if(!p || j > i)
    {
        return 0;
    }
    *e = p->data;
    return 1;
}

//查找链表中是否存在值为e的元素，若存在返回其序列号，若不存在返回0

LNode *LocateElem(LinkList L,ElemType e)
{
    LNode *p = L->next;
    while(p != NULL && p->data != e)
    {
        p = p->next;
    }
    return p;


}

//插入结点，在第i个位置之前插入结点
//步骤：1.先查找到第i个结点
//      (1)指针p指向链表的第一个位置，设置计数器j（初始为1）
//      (2)j<i时遍历链表，p指针不断向后移，j++
//      (3)p == NULL ,i结点不存在，否则查找成功
//      2.插入该结点
//        如果查找成功，生成一个空结点，把数据赋给该结点的data域，然后移动指针
//要使该结点插入，三个要素，链表L,插入的位置i，以及插入得的数据
//切记插入删除操作都对链表进行了了改变需要使用*L

Status InsertList(LinkList *L,int i,ElemType e)
{
    int j = 1;
    LNode * p = *L;
    while(p != NULL && j < i)
    {
        p = p->next;
        j++;
    }
    if(!p || j > i)
        return 0;
    LNode *s = (LNode *)malloc(sizeof(LNode));
    s->data = e;
    s->next = p->next;
    p->next = s;
    return 1;

}
//删除结点
//步骤：1.查找删除的结点的位置
//       (1)进行查找操作（同插入）
//       (2)若要删除的结点存在，进行删除操作，移动指针
Status DeleteList(LinkList *L,int i,ElemType *e)
{
    int j=1;
    LNode *p = *L;
    while(p ->next && j<i)
    {
        p = p->next;
        j++;
    }
    if(!(p->next) || j>i)
    {
        return 0;
    }
    LNode *q = p->next;
    *e = q->data;
    p->next = q->next;
    free(q);
    return 1;

}

//单链表整表删除
//步骤：声明两个指针p q
//      p指向第一个结点，q指向下一个结点
//      释放p，把q的结点赋值给p
Status ClearList(LinkList *L)
{
    LNode *p,*q;
    p = (*L)->next;
    while(p)
    {
        q = p->next;
        free(p);
        p = q;
    }
    (*L)->next = NULL;
    return 1;
}
//打印整个链表
Status PrintList(LinkList L)
{
    LNode *nextnode = L->next;
    while(NULL != nextnode)
    {
        cout<<nextnode->data<<" ";
        nextnode = nextnode->next;
    }
    cout<<endl;
    return 1;
}

int main()
{
    int e;
    LNode *head = (LNode *) malloc(sizeof(LNode));
    head->next = NULL;
    int m, n;
    cin>>n>>m;
    //InitList(head);
    //CreateList(head);
    //PrintList(head);
    CreateListTail(head);
    PrintList(head);
    //GetElem(head,3,&e);
    //LocateElem(head,3);
    InsertList(&head,n,m);
    PrintList(head);
    cin>>n;
    DeleteList(&head,n,&e);
    PrintList(head);
    ClearList(&head);
    if(1 == ClearList(&head))
    {
        printf("清空链表成功");
    }


}