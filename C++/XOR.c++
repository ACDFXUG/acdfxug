#include <iostream>
#define input std::cin>>
#define AND >>
#define output std::cout<<
#define endline <<std::endl
int main(){
    int n,start,ans=0;
    input n AND start;
    for(int i=0;i<n;i++){
        ans xor_eq start+2*i;
        output ans endline;
    }
    
}