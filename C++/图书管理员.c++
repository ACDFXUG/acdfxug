#include <iostream>
#include <algorithm>

int power10(int l){
    int ans=1;
    for(int pow=10;l>0;l>>=1){
        if(l&1) ans*=pow;
        pow*=pow;
    }
    return ans;
}

int main(){
    int n,q;
    std::cin>>n>>q;
    int *book=new int[n];
    for(int i=0;i<n;scanf("%d",book+i++));
    std::sort(book,book+n);
    for(int l,id;q-->0;){
        scanf("%d%d",&l,&id);
        bool found=false;
        for(int i=0;i<n;i++){
            if(book[i]>=id
            &&(book[i]-id)%power10(l)==0){
                printf("%d\n",book[i]);
                found=true;
                break;
            }
        }
        if(!found) puts("-1");
    }
    delete[] book;
}