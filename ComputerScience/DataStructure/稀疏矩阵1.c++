#include<bits/stdc++.h>
using namespace std;
#define MaxSize 100
struct TupNode{
    int row;
    int col;
    int d;

};
struct TSMatrix{
    int rows;
    int cols;
    int nums;
    TupNode data[MaxSize];
};
int a[1000][1000];
void Mdisplay(TSMatrix M){
    for(int i=0;i<M.nums;i++){
        cout<<M.data[i].row<<" "<<M.data[i].col<<" "<<M.data[i].d<<endl;;
    }
}
typedef struct OLNode{
	int row,col;
	int d;
	OLNode *right,*down;
}*OLink;
struct CrossList{
	OLink *rhead, *chead;
	int rows,cols,nums;
};
void CreateMatrix(CrossList* L,TSMatrix M){
    int num = 0;
    OLNode* p = NULL, * q = NULL;
    L->cols=M.cols;
    L->rows=M.rows;
    if (!(L->rhead = (OLink*)malloc((L->rows + 1) * sizeof(OLink))) || !(L->chead = (OLink*)malloc((L->cols + 1) * sizeof(OLink))))
    {
        printf("初始化矩阵失败");
        return;
    }
    for (int i = 0; i <= L->rows; i++)
    {
        L->rhead[i] = NULL;
    }
    for (int j = 0; j <= L->cols; j++)
    {
        L->chead[j] = NULL;
    }
    while (num < M.nums) {
        if (!(p = (OLNode*)malloc(sizeof(OLNode)))){
            printf("初始化三元组失败");
            return;
        }
        p->row=M.data[num].row;
        p->col=M.data[num].col;
        p->d=M.data[num].d;
        num++;
        if (NULL ==L->rhead[p->row] || L->rhead[p->row]->col > p->col){
            p->right=L->rhead[p->row];
            L->rhead[p->row]=p;
        }
        else{
            for (q = L->rhead[p->row]; (q->right) && q->right->col < p->col; q = q->right);
            p->right = q->right;
            q->right = p;
        }
        if (NULL == L->chead[p->col] || L->chead[p->col]->row > p->row){
            p->down = L->chead[p->col];
            L->chead[p->col] = p;
        }
        else{
            for (q = L->chead[p->col]; (q->down) && q->down->row < p->row; q = q->down);
            p->down = q->down;
            q->down = p;
        }
    }
}
void Ldisplay(CrossList L) {
    int i,j;
    for (i=1;i<=L.rows;i++) {
        if (NULL==L.rhead[i]) {
            for (j=1; j<=L.cols;j++) {
                printf("0 ");
            }
            putchar('\n');
        }
        else{
            int n=1;
            OLink p=L.rhead[i];
            while(n<=L.cols) {
                if(!p||(n< p->col)){
                    printf("0 ");
                }
                else{
                    printf("%d ", p->d);
                    p=p->right;
                }
                n++;
            }
            putchar('\n');
        }
    }
}
int main(){
    TSMatrix M;
    CrossList L;
     cout<<"input row and col"<<endl;
    cin>>M.rows>>M.cols;
    M.nums=0;
    cout<<"input matrix"<<endl;
    for(int i=1;i<=M.rows;i++){
        for(int j=1;j<=M.cols;j++){
            cin>>a[i][j];
            if(a[i][j]!=0){
                M.data[M.nums].row=i;
                M.data[M.nums].col=j;
                M.data[M.nums].d=a[i][j];
                M.nums++;
            }
        }
    }
    cout<<"tridisplay"<<endl;
    Mdisplay(M);
    cout<<"crosslist"<<endl;
    L.rhead=L.chead=NULL;
    CreateMatrix(&L,M);
    Ldisplay(L);
}
