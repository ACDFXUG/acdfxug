#include <cstring>
#include <iostream>
using namespace std;
int negative(long long n){
    if(n>0){
        return 0;
    }else{
        return 1;
    }
}

int main() { 
    long long N;
    int R;
    char d[] = "0123456789ABCDEF";
    string result;           
    while (cin>>N>>R) {
        result="";
        long long p=N;   
        if (negative(p)) {
            N=-N;
        }
        while (N > 0) {
            int r=N%R;
            result=d[r]+result;
            N/=R;
        }
        if (negative(p)) {
            result="-"+result;
        }
        cout<<result<<endl;
    }
}