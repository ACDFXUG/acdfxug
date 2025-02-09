#include <iostream>
#include <unordered_map>

std::unordered_map<int,int> rotate={
    {0,0},{1,1},{2,5},{5,2},{6,9},{8,8},{9,6}
};

bool is_good(int x){
    int tmp=x,ans=0,cnt=1;
    for(;tmp;tmp/=10,cnt*=10){
        int t=tmp%10;
        if(!rotate.contains(t)){
            return false;
        }else{
            ans+=rotate[t]*cnt;
        }
    }
    return ans!=x;
}

int rotatedDigits(int n) {
    int ans=0;
    for(int i=1;i<=n;i++){
        int t=is_good(i);
        if(t) ans++;
    }
    return ans;
}

int main(){
    printf("%d\n",rotatedDigits(10));
}