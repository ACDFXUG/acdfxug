#include <iostream>
#include <queue>
#include <unordered_map>
#define HashMap std::unordered_map

class ZigZag{
public:
    std::string word;
    int cnt;
    ZigZag():word(""),cnt(0){}
    ZigZag(std::string word):
    word(word),cnt(0){}
    bool operator <(const ZigZag &zg) const{
        return cnt==zg.cnt?
        word>zg.word:cnt>zg.cnt;
    }
};

int main(){
    int K,N;
    char c;
    std::cin>>K>>N;
    HashMap<char,std::priority_queue<ZigZag>> zig;
    for(int i=0;i<K;i++){
        std::string word;
        std::cin>>word;
        zig[word[0]].push({word});
    }
    for(int i=0;i<N;i++){
        std::cin>>c;
        ZigZag zg=zig[c].top();
        zig[c].pop();
        printf("%s\n",zg.word.c_str());
        zg.cnt++;
        zig[c].push(zg);
    }
}