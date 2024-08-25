#include <iostream>
#include <unordered_map>
typedef long long ll;
int main(){
    std::unordered_map<int,int> num_cnt;
    int n,bi,ci,q;
    ll sum=0ll;
    std::cin>>n;
    for(int i=0,num;i<n;i++){
        scanf("%d",&num);
        num_cnt[num]++;
        sum+=num;
    }
    std::cin>>q;
    while(q-->0){
        scanf("%d%d",&bi,&ci);
        sum+=(ci-bi)*num_cnt[bi];
        num_cnt[ci]+=num_cnt[bi];
        num_cnt.erase(bi);
        printf("%lld\n",sum);
    }
}