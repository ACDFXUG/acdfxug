#include <iostream>
#include <vector>
#define VectorINT std::vector<int>
#define String std::string
#define MAX(a,b) a>b?a:b
#define MIN(a,b) a<b?a:b

VectorINT Mb(VectorINT A){
    int N=A.size();
    VectorINT ans;
    for(int i=0;i<N-1;i++){
        ans.push_back(MAX(A[i],A[i+1]));
    }
    return ans;
}

VectorINT mb(VectorINT A){
    int N=A.size();
    VectorINT ans;
    for(int i=0;i<N-1;i++){
        ans.push_back(MIN(A[i],A[i+1]));
    }
    return ans;
}

int main(){
    int N;
    std::cin>>N;
    VectorINT A(N),B;
    for(int i=0;i<N;scanf("%d",&A[i++]));
    B=A;
    String S;
    std::cin>>S;
    for(char x:S){
        if(x=='M'){
            B=Mb(B);
        }else if(x=='m'){
            B=mb(B);
        }
    }
    printf("%d\n",B[0]);
}