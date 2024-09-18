#include <iostream>

class Test{
public:
    int a,b,c;
    Test(int a,int b,int c):
    a(a),b(b),c(c){}
};

int main(){
    Test test{1,2,3};
    printf("%d %d %d\n",test.a,test.b,test.c);
    // std::cout<<__cplusplus<<std::endl;
}