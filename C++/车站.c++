#include <print>
#include <vector>
#include <numeric>

constexpr int febo[20]{
    1,1,2,3,5,
    8,13,21,34,55,
    89,144,233,377,610,
    987,1597,2584,4181,6765
};

int getA(int x){
    if(x==0||x==1) return 1;
    else if(x==2||x==3) return 2;
    else return 2+std::accumulate(febo,febo+x-4,0);
}

int getS(int x){
    if(x==0||x==1||x==2) return 0;
    return std::accumulate(febo,febo+x-3,0);
}

int get_count(int a,int m,int x,int n){
    if(n==1||n==2) return a;
    if(n==3) return 2*a;
    int s=(m-getA(n-1)*a)/getS(n-1);
    return a*getA(x)+s*getS(x);
}

int main(){
    int a,n,m,x;
    scanf("%d%d%d%d",&a,&n,&m,&x);
    std::println("{}",get_count(a,m,x,n));
}