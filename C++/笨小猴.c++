#include <iostream>
#include <array>
#include <algorithm>

std::array<int,26> letter_cnt(std::string &eng){
    std::array<int,26> cnt{};
    for(char &c:eng){
        cnt[c-'a']++;
    }
    return cnt;
}

std::pair<int,int> get_extream(std::array<int,26> &cnt){
    int min=0x7fffffff,max=0;
    for(int &x:cnt){
        if(x){
            min=std::min(min,x);
            max=std::max(max,x);
        }
    }
    return {min,max};
}

const std::array<int,25> primes{
    02,03,05,07,11,
    13,17,19,23,29,
    31,37,41,43,47,
    53,59,61,67,71,
    73,79,83,89,97,
};

int main(){
    std::string eng;
    std::cin>>eng;
    std::array<int,26> cnt=letter_cnt(eng);
    std::pair<int,int> extream=get_extream(cnt);
    int delta=extream.second-extream.first;
    for(auto &prime:primes){
        if(delta==prime){
            printf("Lucky Word\n%d\n",delta);
            goto RETURN;
        }
    }
    printf("No Answer\n0\n");
    RETURN:
}