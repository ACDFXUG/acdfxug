#include <iostream>
#define String std::string

int main(){
    int N,X;
    scanf("%d%d",&N,&X);
    String x="";
    for(int i=0;i<26;x+=String(N,'A'+i++));
    printf("%c\n",x[X-1]);
}