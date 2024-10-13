#include <iostream>
using namespace std;
long febo(int n){
    if(n==1||n==2){
        return 1;
    }else{
        return febo(n-1)+febo(n-2);
    }
}
