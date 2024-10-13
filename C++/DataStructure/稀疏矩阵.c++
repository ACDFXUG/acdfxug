#include <iostream>
using namespace std;
#define MAXM 100
struct Tup
{
    int row,col,val;
};
struct TSM
{
    int rows,cols,vals;
    Tup data[MAXM];
};
int a[1000][1000];
void Md(TSM M){
    for(int i=0;i<M.vals;i++){
        cout<<M.data[i].row<<" "<<M.data[i].col<<" "<<M.data[i].val<<endl;
    }
}
typedef struct LN
{
    int row,col,val;
    LN *r,*d;
}*OK;
 struct XList
{
    OK *rh,*ch;
    int rows,cols,vals;
};
void creatM(XList* L,TSM M){
    int num=0;
    LN* p=NULL,*q=NULL;
    L->cols=M.cols;
    L->rows=M.rows;
    if(!(L->rh=(OK*)malloc((L->rows+1)*sizeof(OK)))||!(L->ch=(OK*)malloc((L->cols+1)*sizeof(OK)))){
        cout<<"failed";
        return;
    }for(int i=0;i<=L->rows;i++){
        L->rh[i]=NULL;
    }
    for(int j=0;j<=L->cols;j++){
        L->ch[j]=NULL;
    }
    for(;num<M.vals;){
        if(!(p=(LN*)malloc(sizeof(LN)))){
            cout<<"failed"<<endl;
            return;
        }
        p->row=M.data[num].row;
        p->col=M.data[num].col;
        p->val=M.data[num].val;
        num++;
        if(L->rh[p->row]==NULL||L->rh[p->row]->col>p->col){
            p->r=L->rh[p->row];
            L->rh[p->row]=p;
        }else{
            for(q=L->rh[p->row];(q->r)&&q->r->col<p->col;q=q->r);
            p->r=q->r;
            q->r=p;
        }
        if(L->ch[p->col]==NULL||L->ch[p->col]->row>p->row){
            p->d=L->ch[p->col];
            L->ch[p->col]=p;
        }else{
            for(q=L->ch[p->col];(q->d)&&q->d->row<p->row;q=q->d);
            p->d=q->d;
            q->d=p;
        }
    }
}
void Ld(XList L){
    int i,j;
    for(i=1;i<=L.rows;i++){
        if(L.rh[i]==NULL){
            for(j=1;j<=L.cols;j++){
                cout<<"0 ";
            }
            cout<<endl;
        }else{
            int n=1;
            OK p=L.rh[i];
            for(;n<=L.cols;){
                if(!p||(n< p->col)){
                    cout<<"0 ";
                }else{
                    cout<<p->val<<" ";
                    p=p->r;
                }
                n++;
            }
            cout<<endl;
        }
    }
}
int main(){
    TSM M;
    XList L;
    cout<<"input row and col"<<endl;
    cin>>M.rows>>M.cols;
    M.vals=0;
    cout<<"input matrix"<<endl;
    for(int i=1;i<=M.rows;i++){
        for(int j=1;j<M.cols;j++){
            cin>>a[i][j];
            if(a[i][j]!=0){
                M.data[M.vals].row=i;
                M.data[M.vals].col=j;
                M.data[M.vals].val=a[i][j];
                M.vals++;
            }
        }
    }
    cout<<"tridisplay"<<endl;
    Md(M);
    cout<<"Xlist"<<endl;
    L.rh=L.ch=NULL;
    creatM(&L,M);
    Ld(L);
}

