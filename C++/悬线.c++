#include <iostream>
#include <bitset>
#include <unordered_set>
#include <vector>

std::bitset<100001> prime;

int main(){
    prime.set();
    prime[0]=prime[1]=0;
    for(int p=2;p*p<=100000;++p){
        if(prime[p]){
            for(int i=p*p;i<=100000;i+=p){
                prime[i]=0;
            }
        }
    }
    std::unordered_set<int> p;
    for(int i=2;i<=100000;++i) if(prime[i]) p.insert(i);
    int T;
    std::cin>>T;
    for(int n,m;T-->0;){
        std::cin>>n>>m;
        std::vector<std::vector<int>> a(n,std::vector<int>(m,0));
        for(int i=0;i<n;++i){
            for(int j=0;j<m;++j){
                std::cin>>a[i][j];
            }
        }
        for(int i=0;i<n;++i){
            for(int j=0;j<m;++j){
                if(!p.contains(a[i][j])) std::cout<<0<<(j==m-1?'\n':' ');
                else{
                    int k=0;
                    for(int x=i;x>=0;--x){
                        if(p.contains(a[x][j])) ++k;
                        else break;
                    }
                    std::cout<<k<<(j==m-1?'\n':' ');
                }
            }
        }
    }
}