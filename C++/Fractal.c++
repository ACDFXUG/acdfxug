#include <iostream>
#include <vector>
#include <cmath>

size_t pow3(int n){
    size_t result=1,three=3;
    for(;n>0;n>>=1){
        if(n&1) result*=3;
        three*=three;
    }
    return result;
}

std::vector<std::string> fractal(int n){
    if(n==1){
        std::vector<std::string> base={"X"};
        return base;
    }else{
        std::vector<std::string> base=fractal(n-1);
        std::vector<std::string> result;
        int size=base.size();
        for(int i=0;i<size;i++){
            result.push_back(base[i]+std::string(pow3(size-2),' ')+base[i]);
        }
        for(int i=0;i<size;i++){
            std::string mid(2*size+1,' ');
            mid[size]=base[i][0];
            result.push_back(mid);
        }
        for(int i=0;i<size;i++){
            result.push_back(base[i]+std::string(pow3(size-2),' ')+base[i]);
        }
        return result;
    }
}

void printFractal(std::vector<std::string> &fractal){
    for(const auto &line:fractal){
        printf("%s\n",line.c_str());
    }
}

int main(){
    int n;
    std::cin>>n;
    std::vector<std::string> fra=fractal(n);
    printFractal(fra);
    return 0;
}