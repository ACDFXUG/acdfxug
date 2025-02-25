#include <iostream>
#include <bitset>
#include <vector>
#include <cmath>
#include <algorithm>

std::bitset<100000001> primes;

//5<=a<b<=100000000;
std::vector<int> generateOddPalindromesInRange(int a, int b) {
    std::vector<int> result;

    // 确定最小和最大长度
    int minDigits = std::to_string(a).length(); // 最小长度
    int maxDigits = std::to_string(b).length(); // 最大长度

    for (int n = minDigits; n <= maxDigits; ++n) {
        int halfLength = (n + 1) / 2; // 前半部分的长度
        int start = std::pow(10, halfLength - 1); // 最小值
        int end = std::pow(10, halfLength) - 1;   // 最大值

        for (int i = start; i <= end; ++i) {
            std::string firstHalf = std::to_string(i);
            std::string secondHalf = firstHalf;

            if (n % 2 == 1) {
                // 奇数长度，去掉中间一位
                secondHalf.pop_back();
            }

            // 反转后半部分并拼接
            std::reverse(secondHalf.begin(), secondHalf.end());
            std::string palindromeStr = firstHalf + secondHalf;
            int palindrome = std::stoi(palindromeStr);

            // 检查是否为奇数且在 [a, b] 范围内
            if (palindrome >= a && palindrome <= b && palindrome % 2 != 0) {
                result.push_back(palindrome);
            } else if (palindrome > b) {
                break; // 超过范围，停止当前长度的生成
            }
        }
    }

    return result;
}

int main(){
    primes.set();
    primes[0]=primes[1]=0;
    for(int p=2;p*p<=100000001;p++){
        if(primes[p]){
            for(int i=p*p;i<=100000001;i+=p){
                primes[i]=0;
            }
        }
    }
    int a,b;
    scanf("%d%d",&a,&b);
    std::vector palin=generateOddPalindromesInRange(a,b);
    for(const int &p:palin){
        if(primes[p]){
            printf("%d\n",p);
        }
    }
}