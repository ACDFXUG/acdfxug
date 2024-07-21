#include <iostream>

int main(){
    int A,B,C,D;
    std::cin>>A>>B>>C>>D;
    int a=A|B,b=C^D,c=B&C,d=A|D;
    int up=a&b,low=c^d;
    printf("%d\n",up|low);
    return 0;
}