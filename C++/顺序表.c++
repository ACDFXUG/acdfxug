#include <iostream>
#include <algorithm>
using namespace std;

 typedef struct 
 {
    int order[1000];
    int length;
 }S;

 void Initial(S &L){
    L.length=0;
 }

 void Insert(S &L,int p){
    for(int i=0;i<p;i++){
        cin>>L.order[i];
        L.length++;
    }
 }

 int Delete(S &L,int p){
    if(p<0||p>=L.length){
        return 0;
    }
    for(int i=p;i<=L.length;i++){
        L.order[i-1]=L.order[i];
    }
    L.length--;
 }

int Locationfind(S &L,int p){
    if(p<0||p>=L.length){
        return 0;
    }else{
        cout<<L.order[p-1]<<endl;
    }
}

int Valuefind(S &L,int p) {
    for(int i=0;i<L.length;i++){
        if(p==L.order[i]){
            cout<<L.order[i]<<endl;
        }
    }
}

int Print(S &L){
    if(L.length==0){
        return 0;
    }else{
        for(int i=0;i<L.length;i++){
            cout<<L.order[i]<<" ";
        }
    }
}

int Average(S &L){
    if(L.length==0){
        return 0;
    }else if(L.length%2==0){
        int sum=0;
        for(int i=0;i<L.length;i++){
            sum+=L.order[i];
        }
        int p=sum/L.length;
        for(int i=L.length;i>=L.length/2;i--){
            L.order[i]=L.order[i-1];
        }
        L.order[L.length/2-1]=p;
        L.length++;
    }else if(L.length%2==1){
        int sum=0;
        for(int i=0;i<L.length;i++){
            sum+=L.order[i];
        }
        int p=sum/L.length;
        for(int i=L.length;i>=(L.length-1)/2;i--){
            L.order[i]=L.order[i-1];
        }
        L.order[(L.length-1)/2]=p;
        L.length++;
    }
}




 