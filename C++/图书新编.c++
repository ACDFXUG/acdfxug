#include <iostream>
#include <unordered_map>

int power10(int n){
    int ans=1,pow=10;
    while(n){
        if(n&1) ans*=pow;
        pow*=pow;
        n>>=1;
    }
    return ans;
}

int main(){
    int n,q;
    std::cin>>n>>q;
    std::unordered_map<int,int> books;
    for(int i=0,id;i<n;i++){
        scanf("%d",&id);
        books[id]++;
    }
    for(int l,code;q-->0;){
        scanf("%d%d",&l,&code);
        int sum=0;
        for(auto &[id,cnt]:books){
            if(id>=code&&(id-code)%power10(l)==0){
                sum+=cnt;
            }
        }
        printf("%d\n",sum);
    }
}