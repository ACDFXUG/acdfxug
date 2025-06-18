#include <iostream>
#include <cmath>

int main() {
    int n, k, count = 0;
    std::cin >> n >> k;

    long long max_s = static_cast<long long>(sqrt((k * static_cast<long long>(2 * n - k + 1)) / 2)) + 1;

    for (long long s = 1; s <= max_s; ++s) {
        long long sq = s * s;
        if ((sq - (k * (k - 1) / 2)) % k != 0) continue;
        int a = (sq - (k * (k - 1) / 2)) / k;

        if (a >= 1 && a + k - 1 <= n) {
            ++count;
        }
    }

    std::cout << count << std::endl;
    return 0;
}