#include <iostream>
#include <algorithm>
using namespace std;

typedef struct polynomial{
    float coef;
    int expn;
    struct polynomial *next;
}polynomial;
 
// 创建多项式，m为多项式的项数，当m为0时，函数只初始化头结点
void CreatePolyn(polynomial **p, int m){
    int i, data;
    int flag;
    polynomial *cp, *temp;
    (*p) = (polynomial *)malloc(sizeof(polynomial));
    (*p)->coef = 0.0;
    (*p)->expn = -1;
    (*p)->next = NULL;
 
    for(i=1; i<=m; ++i){
        cp = *p;
        flag = 0; //标志多项式中是否已经存在相同指数的多项式
        temp = (polynomial *)malloc(sizeof(polynomial));
        cin>>temp->coef;
        cin>>temp->expn;
 
        while(cp->next && temp->expn > cp->next->expn){
            cp = cp->next;
        }
 
        if(cp->next && temp->expn == cp->next->expn){
            continue;// 如果已经存在相同指数的多项式，忽略该项
        }
 
        temp->next = cp->next;
        cp->next = temp;
    }
}
 
// 打印多项式
void PrintPolyn(polynomial *p){
    polynomial *temp = p->next;
    while(temp){
        printf("%.2fx^%d\n", temp->coef, temp->expn);
        temp = temp->next;
    }
}
 
// 多项式加法
void AddPolyn(polynomial **pa, polynomial **pb){
    polynomial *cpa, *cpb, *temp, *ccpa, *p = (*pa);
    cpa = (*pa)->next;
    cpb = (*pb)->next;
 
    while(cpa && cpb){
        if(cpa->expn < cpb->expn){
            p->next = cpa;
            p = cpa;
            cpa = cpa->next;
        } else if(cpa->expn > cpb->expn) {
            p->next = cpb;
            p = cpb;
            cpb = cpb->next;
        } else {
            if(cpa->coef + cpb->coef == 0){
                temp = *pa;
                while(temp->next != cpa)
                    temp = temp->next;
                temp->next = cpa->next;
                ccpa = cpa;
                cpa = cpa->next;
                free(ccpa);
            } else {
                cpa->coef += cpb->coef;
                p->next = cpa;
                p = cpa;
                cpa = cpa->next;
                cpb = cpb->next;
            }
        }
    }
    if(cpa)
        p->next = cpa;
    else
        p->next = cpb;
    free(*pb);
}
 
// 多项式减法
void SubtractPolyn(polynomial **pa, polynomial **pb){
    polynomial *cpa, *cpb, *temp, *ccpa, *p = (*pa);
    cpa = (*pa)->next;
    cpb = (*pb)->next;
 
    while(cpa && cpb){
        //puts("work?");
        if(cpa->expn < cpb->expn){
            p->next = cpa;
            p = cpa;
            cpa = cpa->next;
        } else if(cpa->expn > cpb->expn) {
            p->next = cpb;
            p = cpb;
            p->coef *= -1; // 改变系数的符号，将减数多项式的系数变为负的
            cpb = cpb->next;
        } else { // cpa->expn == cpb->expn 指数相等
            if(cpa->coef == cpb->coef){ //如果两项系数相等，删除该节点
                temp = *pa;
                while(temp->next != cpa)
                    temp = temp->next;
                temp->next = cpa->next;
                ccpa = cpa;
                cpa = cpa->next;
                cpb = cpb->next;
                free(ccpa);
            } else {
                cpa->coef -= cpb->coef;
                p->next = cpa;
                p = cpa;
                cpa = cpa->next;
                cpb = cpb->next;
            }
        }
    }
    // 注意：cpa和cpb不是互斥关系，需分开两个if分支，但最多只有一个分支执行，可能同时不执行
    if(cpa)
        p->next = cpa;
 
    if(cpb){
        p->next = cpb;
        while(cpb){
            cpb->coef *= -1; // 改变系数的符号，将减数多项式的系数变为负的
            cpb = cpb->next;
        }
    }
    free(*pb);
}
 
//将pb中的多项式复制到pa中
void CopyPolyn(polynomial **pa, polynomial *pb){
 
    CreatePolyn(pa, 0);
    polynomial *temp, *cpa;
    cpa = *pa;
    pb = pb->next; // 移动指针指向第一个节点
    while(pb){
        temp = (polynomial *)malloc(sizeof(polynomial));
        temp->coef = pb->coef;
        temp->expn = pb->expn;
        temp->next = NULL;
 
        cpa->next = temp;
        cpa = temp;
        pb = pb->next;
    }
}
 
//pa为多项式。pb为单项式，与pa中的每一项相乘，结果保存到pa中
void MultiplyOperate(polynomial *pa, polynomial *pb){
 
    pa = pa->next;
    while(pa){
        pa->coef *= pb->coef;
        pa->expn += pb->expn;
        pa = pa->next;
    }
}
 
// 多项式乘法
void MultiplyPolyn(polynomial **pa, polynomial **pb){
    // pa应该在此过程中保持不变，直到得到最后的结果
    polynomial *cpa, *ccpa, *res;
    cpa = *pa; //保存着原pa的内容
 
    CreatePolyn(pa, 0); //从新初始化pa为头结点
    (*pb) = (*pb)->next;
    while(*pb){
        CopyPolyn(&ccpa, cpa);
        MultiplyOperate(ccpa, *pb);
        AddPolyn(pa, &ccpa);
        (*pb) = (*pb)->next;
    }
}
 
int main(){
    polynomial *pa, *pb;
    int m,n;
    cin>>m>>n;
    CreatePolyn(&pa, m);
    CreatePolyn(&pb, n);
    AddPolyn(&pa,&pb);
    PrintPolyn(pa);
    SubtractPolyn(&pa, &pb);
    PrintPolyn(pa);
    MultiplyPolyn(&pa, &pb);
    //SubtractPolyn(&pa, &pb);
    PrintPolyn(pa);
    return 0;
}