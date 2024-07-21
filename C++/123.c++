#include <iostream>
using namespace std;
int *PLUS(int a[10],int b[10]){
    int *c=new int[10];
    for(int i=0;i<10;i++){
        c[i]=a[i]+b[i];
    }
    return c;
}
int main(){
    int a[10];
    int b[10];
    for(int i=0;i<10;i++){
        a[i]=i;
        b[i]=-i;
    }
    for(int i=0;i<10;i++){
        cout<<PLUS(a,b)[i];
    }
}