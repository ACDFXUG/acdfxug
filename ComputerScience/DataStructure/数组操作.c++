#include <iostream>
using namespace std;
#define SIZE 1000
struct Array
{
    int *arr;
    int length;
};
void Init(Array &a,int len){
    if(len>SIZE){
        cout<<"too big"<<endl;
        return;
    }
    a.length=len;
    a.arr=new int[a.length];
}
void destory(Array &a){
    delete[] a.arr;
    cout<<"complete"<<endl;
}
int value(Array a,int i){
    if(i<0||i>=a.length){
        cout<<"over"<<endl;
        return -1;
    }
    return a.arr[i];
}
void assign(Array &a,int i,int value){
    if(i<0||i>=a.length){
        cout<<"over"<<endl;
        return;
    }
    a.arr[i]=value;
}
int main(){
    Array A;
    int a;
    cout<<"size"<<endl;
    cin>>a;
    Init(A,a);
    int b;
    cout<<"inputnumber"<<endl;
    cin>>b;
    cout<<"input"<<endl;
    int element;
    for(int i=0;i<b;i++){
        cin>>element;
        assign(A,i,element);
    }
    for(int ord;cin>>ord;){
        if(ord==1){
            int down,ele;
            cin>>down>>ele;
            assign(A,down,ele);
        }
        if(ord==2){
        int down;
        cin>>down;
        cout<<value(A,down)<<endl;
    }
    if(ord==3){
        for(int i=0;i<b;i++){
            cout<<value(A,i)<<" ";
        }
        cout<<endl;
    }
    if(ord==0){
        destory(A);
        break;
    }
    }
}