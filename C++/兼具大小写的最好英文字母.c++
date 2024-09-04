#include <iostream>
#include <unordered_set>
#include <set>
#include <stdint.h>
using namespace std;

bool better=[](char A,char B){
    return A>B;
};

string greatestLetter(string s) {
    unordered_set<char> character;
    for(char &c:s){
        character.insert(c);
    }
    set<char,decltype(better)> letters;
    for(char c='A';c<='Z';c++){
        if(character.contains(c)
        &&character.contains(c+32)){
            letters.insert(c);
        }
    }
    return letters.empty()?"":string{*letters.begin()};
}

int main(){
    string s="arRAzFif";
    cout<<greatestLetter(s);
}