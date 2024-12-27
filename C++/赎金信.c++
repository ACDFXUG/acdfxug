#include <iostream>
#include <array>
using namespace std;

array<int,26uz> to_arr(string s){
    array<int,26uz> a;
    for(char c:s){
        a[c-'a']++;
    }
    return a;
}

bool canConstruct(string ransomNote, string magazine) {
    auto ransom_arr=to_arr(ransomNote);
    auto magazine_arr=to_arr(magazine);
    for(auto i=0uz;i<26uz;i++){
        if(ransom_arr[i]>magazine_arr[i]){
            return false;
        }
    }
    return true;
}