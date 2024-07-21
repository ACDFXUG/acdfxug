#include <iostream>
#define String std::string

int main(){
    int A,B,N;String X;
    std::cin>>A>>B>>N>>X;
    for(int i=0;i<N;i++){
        char xi=X[i];
        if(xi=='S'&&A>0){
            A--;
        }else if(xi=='C'&&B>0){
            B--;
        }else if(xi=='E'){
            if(A>B)A--;
            else if(A<B)B--;
            else if(A>0)A--;
        }
    }
    printf("%d\n%d\n",A,B);
}