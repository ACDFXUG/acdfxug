#include <iostream>
#include <unordered_set>
#include <print>
using namespace std::string_literals;

const size_t fibonacci[]={
    1,1,2,3,5,8,13,21,34,55,89,144,233,377,
    610,987,1597,2584,4181,6765,10946,17711,
    28657,46368,75025,121393,196418,317811,
    514229,832040,1346269,2178309,3524578,
    5702887,9227465,14930352,24157817,39088169,
    63245986,102334155,165580141,267914296,
    433494437,701408733,1134903170,1836311903,
};

int main(){
    std::unordered_set<size_t> fibo_multi;
    for(int i=0;i<46;i++){
        for(int j=0;j<46;j++){
            fibo_multi.insert(fibonacci[i]*fibonacci[j]);
        }
    }
    fibo_multi.insert(0uz);
    int T;
    scanf("%d",&T);
    for(int i=0,n;i<T;i++){
        scanf("%d",&n);
        std::println("{}",fibo_multi.contains(n)?"TAK":"NIE");
    }
}
