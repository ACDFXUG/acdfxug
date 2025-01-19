#include <iostream>

int powx(int x,int n){
    int ans=1;
    for(;n>0;n>>=1){
        if(n&1) ans*=x;
        x*=x;
    }
    return ans;
}

int main(){
    int n,m,k;
    scanf("%d%d%d",&n,&m,&k);
    std::string *pic=new std::string[m];
    for(int i=0;i<m;i++){
        pic[i]=std::string(n,'.');
    }
    int *a=new int[k+1];
    for(int i=0;i<=k;scanf("%d",a+i++));
    auto fx=[&](int x){
        int ans=0;
        for(int i=0;i<=k;i++){
            ans+=a[i]*powx(x,i);
        }
        return ans;
    };
    for(int j=0;j<n;j++){
        int y=fx(j);
        if(y<m&&y>=0){
            pic[m-y-1][j]='*';
        }
    }
    for(int i=0;i<m;i++){
        printf("%s\n",pic[i].c_str());
    }
    delete[] pic,a;
}