#include <iostream>
#include <cstring>
#include <set>
typedef std::string String;

class Word{
    private:
    int upper[26]{};
    public:
    Word(String word){
        for(char x:word){
            this->upper[x-'A']++;
        }
    }
    bool operator ==(const Word &w) const{
        for(int i=0;i<26;i++){
            if(upper[i]!=w.upper[i]) return false;
        }
        return true;
    }
};

int main(){
    int N;
    std::cin>>N;
    auto compare=[](const Word &w1,const Word &w2){
        return w1==w2;
    };
    std::set<Word,decltype(compare)> words;
    for(String word;N-->0;words.insert(Word(word))){
        std::cin>>word;
    }
    printf("%d\n",words.size());
}