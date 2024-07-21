#include <iostream>
#include <vector>
#define VectorINT std::vector<int>

VectorINT unique(VectorINT b){
    VectorINT x=b;
    int l=x.size();
    for(int i=0;i<l-1;i++){
        if(x[i]==x[l-1]){
            x.pop_back();
            return x;
        }
    }
    return x;
}

int main(){
    int p,n,end=1;
    std::cin>>p>>n;
    VectorINT a;
    int *x=new int[n];
    for(int i=0;i<n;scanf("%d",x+i++));
    for(int i=0;i<n;i++){
        a.push_back(x[i]%p);
        a=unique(a);
        if(a.size()<i+1){
            printf("%d\n",i+1);
            if(i<n-1){
                end=0;
            }
            break;
        }
    }
    if(end){
        printf("-1\n");
    }
    delete[] x;
}