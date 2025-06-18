#include <iostream>
#include <unordered_map>
#include <algorithm>

const std::unordered_map<char,char> GENE{
    {'A','T'},
    {'T','A'},
    {'C','G'},
    {'G','C'}
};

int main(){
    int T,len;
    size_t stability=0;
    scanf("%d",&T);
    for(std::string str;T-->0;stability=0){
        std::cin>>len>>str;
        auto rts(str);
        std::reverse(rts.begin(),rts.end());
        for(int i=0;i<str.size();++i){
            if(!GENE.contains(str[i])||!GENE.contains(rts[i])){
                stability=0;
                break;
            } 
            if(GENE.at(str[i])==rts[i]){
                stability+=i+1;
            }
        }
        printf("%zu\n",stability);
    }
}