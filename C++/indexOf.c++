#include <iostream>
#define String std::string

int indexOf(String x,String _str){
    int index=x.find(_str);
    return index==x.npos?-1:index;
}

int main(){
    String a,b;
    std::cin>>a>>b;
    printf("%d\n",indexOf(a,b));
}