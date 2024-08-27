#include <iostream>
#include <unordered_map>

std::unordered_map<char,int> hole_cnt{
    {'C',0},{'E',0},{'F',0},{'G',0},
    {'H',0},{'I',0},{'J',0},{'K',0},
    {'L',0},{'M',0},{'N',0},{'S',0},
    {'T',0},{'U',0},{'V',0},{'W',0},
    {'X',0},{'Y',0},{'Z',0},{'A',1},
    {'D',1},{'O',1},{'P',1},{'Q',1},
    {'R',1},{'B',2},
};

int main(){
    char utpc[4];
    scanf("%s",utpc);
    printf(hole_cnt[utpc[0]]==0
        &&hole_cnt[utpc[1]]==0
        &&hole_cnt[utpc[2]]==1
        &&hole_cnt[utpc[3]]==0?"yes\n":"no\n");
}