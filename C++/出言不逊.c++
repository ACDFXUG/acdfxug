#include <iostream>
#include <queue>
#include <unordered_map>

struct CharCnter{
    char ch;
    __int128_t cnt;
    CharCnter(const char &ch,const __int128_t &cnt):ch(ch),cnt(cnt){}
    CharCnter(CharCnter &&)=default;
    CharCnter(const CharCnter &)=default;
    CharCnter &operator =(CharCnter &&)=default;
    CharCnter &operator =(const CharCnter &)=default;
    bool operator <(const CharCnter &other) const{
        return cnt==other.cnt?ch<other.ch:cnt<other.cnt;
    }
};

int main(){
    std::ios::sync_with_stdio(false);
    std::cin.tie(nullptr);
    std::cout.tie(nullptr);

    std::string str;
    std::cin>>str;
    size_t tmp;
    std::cin>>tmp;
    const __int128_t supL=tmp;
    std::priority_queue<CharCnter> cnt_order;
    std::unordered_map<char,__int128_t> chcnt;
    __int128_t L=str.size();
    if(L>=supL) return (std::cout<<0<<'\n',0);
    for(const char &ch:str) ++chcnt[ch];
    for(const auto &[ch,cnt]:chcnt) cnt_order.emplace(ch,cnt);
    int act=0;
    auto max=cnt_order.top();
    cnt_order.pop();
    for(;L<supL;++act){
        L+=max.cnt;
        max.cnt<<=1;
    }
    std::cout<<act<<std::endl;
}