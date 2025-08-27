#include <iostream>

int mirrorReflection(int p, int q) {
    int l,h;
    for(l=1;(l*q)%p!=0;++l);
    h=(l*q)/p;
    if((h&1)==1){
        return (l&1)==0?2:1;
    }else{
        return (l&1)==0?3:0;
    }
}

int main() {
    std::cout << mirrorReflection(2,1) << std::endl;
    return 0;
}