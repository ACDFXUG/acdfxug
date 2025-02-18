#include <iostream>
//计算所有小于等于 n 的非负整数中数字 1 出现的个数
int countDigitOne(int n) {
    if(n<=0) return 0;
    if(n<=9) return 1;
    int64_t count = 0,N(n);
    for(int64_t i(1);i<=N;i*=10z){
        int64_t divider = i*10z;
        count += (N/divider)*i + std::min(std::max(N%divider-i+1,0z),i);
    }
    return count;
}

int main(){
    std::cout<<countDigitOne(100000)<<std::endl;
    return 0;
}