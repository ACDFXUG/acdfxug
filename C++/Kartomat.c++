#include <iostream>

char board[4][9]={
    "***ABCDE",
    "FGHIJKLM",
    "NOPQRSTU",
    "VWXYZ***"
};

int main(){
    int n;
    std::cin>>n;
    int city[26]{};
    std::string *cities=new std::string[n],prefix;
    for(int i=0;i<n;i++){
        std::cin>>cities[i];
    }
    std::cin>>prefix;
    for(int i=0;i<n;i++){
        if(cities[i].starts_with(prefix)){
            city[cities[i][prefix.length()]-'A']++;
        }
    }
    delete[] cities;
    for(int i=0;i<4;i++){
        for(int j=0;j<8;j++){
            if(!city[board[i][j]-'A']){
                board[i][j]='*';
            }
        }
        printf("%s\n",board[i]);
    }
}