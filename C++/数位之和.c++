#include <iostream>
#include <print>
using namespace std::string_literals;

auto is_beautiful(int n){
    int sum=0;
    for(;n;n/=10){
        sum+=n%10;
    }
    return sum%7==0?"Yes"s:"No"s;
}

int main(){
    int n;
    scanf("%d",&n);
    for(int i=1,x;i<=n;i++){
        scanf("%d",&x);
        std::println("{}",is_beautiful(x));
    }
}