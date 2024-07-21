#include <iostream>

int main(){
    int N,A,B;
    std::cin>>N>>A>>B;
    int n=N/(A+B),rest=N-n*(A+B);
    if(rest==0){
        printf("Bug\n");
    }else{
        printf(rest>A?"Bug\n":"Ant\n");
    }
}