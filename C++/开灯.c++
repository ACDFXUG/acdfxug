#include <iostream>
#include <string>

std::string powerOfTen(int n){
    return "1" + std::string(n, '0');
}

std::string lighton(std::string &light, int i){
    light.reserve(i); // 预分配内存
    return std::string(i - light.size(), '0') + light;
}

std::string lightchange(std::string &light, int i){
    int l= light.size();
    light[l-i-1] = (light[l-i-1] == '0') ? '1' : '0'; // 直接修改字符，假设索引有效
    return light;
}

int main(){
    std::string light("0");
    int n;
    std::cin >> n;
    
    for(int s = 1; s <= n; ++s){
        double a; int t;
        std::cin >> a >> t;
        for(int q = 1; q <= t; ++q){
            int i = static_cast<int>(q * a);
            light = (i >= light.size()) ?
                   lighton(light, i) :
                   lightchange(light, i);
        }
    }
    auto pos = light.find('1');
    std::cout << (light.size() - pos - 1) << '\n';
}