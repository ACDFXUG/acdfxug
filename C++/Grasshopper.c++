#include <iostream>
#include <algorithm>
#include <vector>
#define String std::string

const char aeiou[]{'A','E','I','O','U','Y'};

bool contain(char p){
    for(char x:aeiou){
        if(x==p){
            return true;
        }
    }
    return false;
}

int main(){
    String grass;
    std::cin>>grass;
    grass="#"+grass+"#";
    int l=grass.length(),max=0x80000000;
    std::vector<int> LOCATION;
    LOCATION.push_back(0);
    for(int i=1;i<l-1;i++){
        if(contain(grass[i])){
            LOCATION.push_back(i);
        }
    }
    LOCATION.push_back(l-1);
    for(int delta,i=0;i<LOCATION.size()-1;i++){
        delta=LOCATION[i+1]-LOCATION[i];
        max=delta>max?delta:max;
    }
    printf("%d\n",max);
}