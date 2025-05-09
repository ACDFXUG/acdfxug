#include <iostream>

constexpr int getLastDigitNotZeroOfFactorial(int n) {
    if (n == 0 || n == 1) return 1;
    
    // 分解阶乘中的因子，统计2的个数
    int countTwos = 0;
    long long result = 1;
    constexpr int MOD = 100000; // 足够大的模数以保持足够的精度
    for (int i = 2; i <= n; i++) {
        int current = i;
        
        // 提取2的因子
        while (current % 2 == 0) {
            countTwos++;
            current /= 2;
        }
        
        // 提取5的因子
        int countFives = 0;
        while (current % 5 == 0 && countTwos > 0) {
            countFives++;
            current /= 5;
            countTwos--; // 每有一个5就抵消一个2
        }
        
        // 将剩余部分乘以结果
        if (current > 1) {
            result = (result * current) % MOD;
        }
    }
    
    // 处理剩余的2因子
    for (int i = 0; i < countTwos; i++) {
        result = (result * 2) % MOD;
    }
    
    // 去除可能产生的尾随零
    while (result % 10 == 0) {
        result /= 10;
    }
    
    return result % 10;
}

int main(){
    int n;
    std::cin >> n;
    std::cout << getLastDigitNotZeroOfFactorial(n) << std::endl;
    return 0;
}