#include <iostream>
#include <cstring>
using namespace std;
int Array[]={1,8,6,4,10,5,3,2,22};
void DIS_INSERT(int a[]){
    for(int i=1,j;i<9;i++){
        int s=a[i];  
        for(j=i-1;j>=0&&a[j]>s;j--){
            a[j+1]=a[j];
        }
        a[j+1]=s;
    }
    cout<<"distant insert"<<endl;
    for(int k=0;k<9;k++){
        cout<<a[k]<<" ";
    }
    cout<<endl;
}

void BIN_INSERT(int a[]){
    for(int i=1;i<9;i++){
        int l=0,r=i-1,s=a[i];
        for(;l<=r;){
            int mid=(l+r)/2;
            if(a[mid]>a[i]){
                r=mid-1;
            }else{
                l=mid+1;
            }
        }
        for(int j=i-1;j>=l;j--){
            a[j+1]=a[j];    
        }
        a[l]=s;
    }
    cout<<"binary insert"<<endl;
    for(int k=0;k<9;k++){
        cout<<a[k]<<" ";
    }
    cout<<endl;
}

void QUICK_SORT(int a[],int l,int r){
    if(l>=r){
        return;
    }
    int i=l,j=r,p=a[l];
    for(;i<j;){
        for(;i<j&&a[j]>=p;j--);
        a[i]=a[j];
        for(;i<j&&a[i]<=p;i++);
        a[j]=a[i];
    }
    a[i]=p;
    cout<<"the position "<<i<<endl;
    for(int k=0;k<9;k++){
        cout<<a[k]<<" ";
    }
    cout<<endl;
    QUICK_SORT(a,l,i-1);
    QUICK_SORT(a,i+1,r);
}


int main(){
    int a[9],b[9],c[9];
    memcpy(a,Array,sizeof(Array));
    memcpy(b,Array,sizeof(Array));
    memcpy(c,Array,sizeof(Array));
    DIS_INSERT(a);
    BIN_INSERT(b);
    QUICK_SORT(c,0,8);
}