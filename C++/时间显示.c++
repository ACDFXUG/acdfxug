#include <iostream>
#include <print>
using uz=unsigned long long;

int main(){
    uz ms;  //从1970年1月1日00:00:00到当前时刻经过的毫秒数
    scanf("%zu",&ms);
    std::println("{:02d}:{:02d}:{:02d}",
        (ms/1000/60/60)%24,
        (ms/1000/60)%60,
        (ms/1000)%60);
}