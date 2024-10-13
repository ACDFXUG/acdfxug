#include <iostream>
#include <vector>
using namespace std;
#define MAXM 1000
struct str
{
    char data[MAXM];
    int len;
};
void Initi(str *s){
    s->len=0;
    return;
}
int strl(str *s){
    int p;
    for(p=0;s->data[p]!='\0';p++){}
    return p;
}
void creat(str *s){
    cin>>s->data;
    s->len=strl(s);
}
int empty(str *s){
    return (s->len==0)?1:0;
}
void print(str *s){
    if(empty(s)){
        cout<<"空"<<endl;
        return;
    }
    for(int i=0;i<s->len;i++){
        cout<<s->data[i];
    }
    cout<<endl;
}
int indexof(str *t, str *p){
    int M=p->len;
    int N=t->len;
    for(int i=0;i<=N-M;i++){
        int j;
        for(j=0;j<M;j++){
            if(p->data[j]!=t->data[i+j]){
                break;
            }
        }
        if(j==M){
            return i;
        }
    }
    return -1;
}
vector<int> getNext(str *p){
    int M=p->len;
    vector<int> next(M);
    int i=0,j=-1;
    next[0]=-1;
    for(;i<M-1;){
        if(j==-1||p->data[i]==p->data[j]){
            i++;
            j++;
            next[i]=(p->data[i]!=p->data[j])?j:next[j];
        }else{
            j=next[j];
        }
    }
    return next;
}
int KMP(str *t,str *p){
    int N=t->len;
    int M=p->len;
    vector<int> next=getNext(p);
    int i=0,j=0;
    for(;i<N&&j<M;){
        if(j==-1||t->data[i]==p->data[j]){
            i++;
            j++;
        }else{
            j=next[j];
        }
    }
    return (j==M)?i-j:-1;
}
int main(){
    str *s=new str;
    str *t=new str;
    char m;
    Initi(s);
    Initi(t);
    creat(s);
    creat(t);
    cout<<"simplelocation"<<endl;
    cout<<indexof(s,t)<<endl;
    cout<<"KMPlocation"<<endl;
    cout<<KMP(s,t)<<endl;
}