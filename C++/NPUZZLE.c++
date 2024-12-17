#include <iostream>
#include <unordered_map>

struct Location{
    int x,y;
};

const std::unordered_map<char,Location> std_lct={
    {'A',{0,0}},{'B',{0,1}},{'C',{0,2}},{'D',{0,3}},
    {'E',{1,0}},{'F',{1,1}},{'G',{1,2}},{'H',{1,3}},
    {'I',{2,0}},{'J',{2,1}},{'K',{2,2}},{'L',{2,3}},
    {'M',{3,0}},{'N',{3,1}},{'O',{3,2}},{'.',{3,3}}
};

int main(){
    std::string board[4];
    int manhatonn=0;
    for(int i=0;i<4;i++){
        std::cin>>board[i];
    }
    for(int i=0;i<4;i++){
        for(int j=0;j<4;j++){
            char c=board[i][j];
            if(c!='.'){
                Location lct=std_lct.at(c);
                manhatonn+=abs(i-lct.x)+abs(j-lct.y);
            }
        }
    }
    printf("%d\n",manhatonn);
}