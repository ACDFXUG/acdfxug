#include <iostream>
#define String std::string
#define PairII std::pair<int,int>

const String keyboard[]={
    "qwertyuiop",
    "asdfghjkl;",
    "zxcvbnm,./",
};


PairII locate(char p){
    PairII ans(0,0);
    for(int i=0;i<3;i++){
        for(int j=0;i<10;j++){
            if(keyboard[i][j]==p){
                ans.first=i;
                ans.second=j;
                return ans;
            }
        }
    }
    return ans;
}

int main(){
    char act;
    String sentence;
    std::cin>>act>>sentence;
    if(act=='R'){
        for(char x:sentence){
            PairII a=locate(x);
            printf("%c",keyboard[a.first][a.second-1]);
        }
        printf("\n");
    }else if(act=='L'){
        for(char x:sentence){
            PairII a=locate(x);
            printf("%c",keyboard[a.first][a.second+1]);
        }
        printf("\n");
    }
}