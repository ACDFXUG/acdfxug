#include <iostream>
#include <algorithm>
#include <cmath>
#include <vector>

struct Member {
    std::string name;
    long long sigma; // 放大后的整数值
    bool operator<(const Member& other) const {
        return sigma > other.sigma || (sigma == other.sigma && name < other.name);
    }
};

long long calculate_scaled_sigma(const std::vector<int>& scores) {
    long double sum = 0;
    for(int s : scores) sum += s;
    long double mean = sum / scores.size();
    long double variance = 0;
    for(int s : scores) variance += (s-mean)*(s-mean);
    return llround(1e7 * sqrt(variance/scores.size())); // 关键改进
}

int main() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(nullptr);
    
    int n, m;
    std::cin >> n >> m;
    std::vector<Member> members(n);
    
    for(auto& mem : members) {
        std::cin >> mem.name;
        std::vector<int> scores(m);
        for(auto& s : scores) std::cin >> s;
        mem.sigma = calculate_scaled_sigma(scores);
    }
    
    std::sort(members.begin(), members.end());
    
    for(int i = 0; i < std::min(20, n); ++i)
        std::cout << members[i].name << '\n';
}