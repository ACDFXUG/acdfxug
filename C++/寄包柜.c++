#include <iostream>
#include <unordered_map>
#include <print>

template<class K,class V>
using hashmap=std::unordered_map<K,V>;

template<class T>
void println(T &t){
    std::println("{}",t);
}

int main(){
    int n,q;
    scanf("%d%d",&n,&q);
    hashmap<int,hashmap<int,int>> locker(n);
    for(int s=0,act,i,j,k;s<q;++s){
        scanf("%d%d%d",&act,&i,&j);
        switch(act){
            case 1:{
                scanf("%d",&k);
                locker[i][j]=k;
                break;
            }case 2:{
                int &thing=locker[i][j];
                if(thing!=0){
                    println(thing);
                }
                break;
            }
        }
    }
}
