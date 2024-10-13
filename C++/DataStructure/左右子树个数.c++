int num[];
int MAX=-0x3f3f3f3f;
int max(int a,int b){
    return (a>b)?a:b;
}
void Deepth(Node T,int k){
	if(T==NULL){
        return;
    }
	num[k]++;
	MAX=max(MAX,num[k]);
	Deepth(T->lchild,k+1);
	Deepth(T->rchild,k+1);
}