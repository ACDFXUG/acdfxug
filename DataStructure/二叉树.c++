#include <iostream>
using namespace std;
typedef struct BTNode{
    char data;
    BTNode *lchild,*rchild;
}BTNode,*BT;
void CreatTree(BT &T){
    char p;
    cin>>p;
    if(p=='?'){
        T=NULL;
    }else{
        T=new BTNode;
        T->data=p;
        CreatTree(T->lchild);
        CreatTree(T->rchild);
    }
}
void PRE(BT T){
    if(T){
        cout<<T->data;
        PRE(T->lchild);
        PRE(T->rchild);
    }
}
void MID(BT T){
    if(T){
        MID(T->lchild);
        cout<<T->data;
        MID(T->rchild);
    }
}
void END(BT T){
    if(T){
        END(T->lchild);
        END(T->rchild);
        cout<<T->data;
    }
}
int leave(BT T){
    if(T==NULL){
        return 0;
    }else if(T->lchild==NULL&&T->rchild==NULL){
        return 1;
    }else{
        return leave(T->lchild)+leave(T->rchild);
    }
}
int DEPTH(BT T){
    if(T==NULL){
        return 0;
    }else{
        return max(DEPTH(T->lchild)+1,DEPTH(T->rchild)+1);
    }
}
int main(){
    BT T;
    cout<<"input tree ,? means empty"<<endl;
    CreatTree(T);
    cout<<"PRE"<<endl;
    PRE(T);
    cout<<endl;
    cout<<"MID"<<endl;
    MID(T);
    cout<<endl;
    cout<<"END"<<endl;
    END(T);
    cout<<endl;
    cout<<"leave"<<endl;
    cout<<leave(T)<<endl;
    cout<<"DEPTH"<<endl;
    cout<<DEPTH(T)<<endl;
}