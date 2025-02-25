#include <iostream>
#include <unordered_map>

template<class K,class V>
using hash_map=std::unordered_map<K,V>;

int main(){
    int n;
    scanf("%d",&n);
    hash_map<std::string,bool> names;
    for(int i=0;i<n;++i){
        std::string s;
        std::cin>>s;
        names[s]=false;
    }
    int m;
    scanf("%d",&m);
    for(int i=0;i<m;++i){
        std::string name;
        std::cin>>name;
        if(!names.contains(name)){
            printf("WRONG\n");
        }else{
            if(names[name]){
                printf("REPEAT\n");
            }else{
                printf("OK\n");
                names[name]=true;
            }
        }
    }
}