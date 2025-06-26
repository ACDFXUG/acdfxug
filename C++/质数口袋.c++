#include <iostream>
#include <map>
#include <bitset>

int main(){
    std::bitset<100001> prime;
    prime.set();
    prime[0]=prime[1]=0;
    for(int p=2;p*p<100001;++p){
        if(prime[p]){
            for(int i=p*p;i<100001;i+=p){
                prime[i]=0;
            }
        }
    }
    std::map<int,int> prefix;
    int cnt=0,sum=0;
    for(int p=2;p<100001;++p){
        if(prime[p]){
            ++cnt;
            sum+=p;
            prefix[sum]=cnt;
        }
    }
    int L;
    std::cin>>L;
    if(L<=1){
        std::cout<<0<<'\n';
        return 0;
    }
    auto it=prefix.upper_bound(L);
    if(it!=prefix.begin())[[likely]]{
        --it;
    }
    const auto &[_,c]=*it;
    for(int i=1,p=2;i<=c;++p){
        if(prime[p]){
            std::cout<<p<<'\n';
            ++i;
        }
    }
    std::cout<<c<<'\n';
}