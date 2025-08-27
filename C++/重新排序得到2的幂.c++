#include <iostream>
#include <vector>
#include <cmath>
#include <print>

bool reorderedPowerOf2(int n) {
    auto N=std::to_string(n);
    std::sort(N.begin(),N.end());
    const int len=N.length();
    std::vector<long long> power;
    for(int p=(len-1)*log2(10);p<len*log2(10);++p)
        power.push_back(1ll<<p);
    for(auto &p:power){
        std::string s=std::to_string(p);
        std::sort(s.begin(),s.end());
        if(s==N)
            return true;
    }
    return false;
}

int main() {
    std::cout << reorderedPowerOf2(1) << std::endl;
    std::cout << reorderedPowerOf2(10) << std::endl;
    std::cout << reorderedPowerOf2(16) << std::endl;
    std::cout << reorderedPowerOf2(24) << std::endl;
    std::cout << reorderedPowerOf2(42) << std::endl;
    return 0;
}