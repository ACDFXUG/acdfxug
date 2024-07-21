#include <iostream>
#define String std::string
#define Ull unsigned long long

String isNeeded(int *A,int *B,int N){
    Ull ans=0ull;
    for(int i=0;i<N;i++){
        ans+=A[i];
        if(ans>B[i]){
            return "No";
        }
    }
    return "Yes";
}

int main(){
    int N;
    std::cin>>N;
    int *A=new int[N],*B=new int[N];
    for(int i=0;i<N;i++){
        scanf("%d%d",A+i,B+i);
    }
    std::cout<<isNeeded(A,B,N)<<'\n';
}