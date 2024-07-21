#include "String.h"
#define println(x) printf((x+"\n").c_str());
#define output(x) std::cout<< x <<std::endl;
using namespace std;

int main(){
    String a;
    cin>>a;
    cout<<~a;
}