#include <iostream>
#include <bitset>
#include <vector>

std::bitset<5001> prime;

int main(){
    prime.set();
    prime[0]=prime[1]=0;
    for(int i=2;i*i<=5000;++i){
        if(prime[i]){
            for(int j=i*i;j<=5000;j+=i){
                prime[j]=0;
            }
        }
    }
    std::vector<int> primes;
    for(int i=2;i<=5000;++i){
        if(prime[i]){
            primes.push_back(i);
        }
    }
    int n,x,y;
    scanf("%d%d%d",&n,&x,&y);
    std::vector matrix(n,std::vector<int>(n,0));
    int X=0,Y=0;
    for(int len=n*n,s=0;s<len;){
        while(Y<n&&matrix[X][Y]==0){
            matrix[X][Y++]=primes[s++];
        }
        --Y;
        ++X;
        while(X<n&&matrix[X][Y]==0){
            matrix[X++][Y]=primes[s++];
        }
        --X;
        --Y;
        while(Y>=0&&matrix[X][Y]==0){
            matrix[X][Y--]=primes[s++];
        }
        ++Y;
        --X;
        while(X>=0&&matrix[X][Y]==0){
            matrix[X--][Y]=primes[s++];
        }
        ++X;
        ++Y;
    }
    printf("%d\n",matrix[x-1][y-1]);
}