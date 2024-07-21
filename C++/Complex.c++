#include "Complex.h"
using std::cin;
using std::cout;
using std::endl;
typedef Complex cx;
#define PRINTLN(x) cout<<(x)<<endl
#define PI 3.141592653589793238462643383279502884L
int main(){
    auto z=cx::polar(PI/4);
    PRINTLN(z);
}