#include <iostream>
#include <unordered_set>
#include <unordered_map>
#include <stack>

template<class T>
using hashset=std::unordered_set<T>;

template<class K,class V>
using hashmap=std::unordered_map<K,V>;

int main(){
    std::string n;
    int k;
    std::cin>>n>>k;
    hashmap<char,char> rule;
    for(int i=0;i<k;++i){
        char a,b;
        std::cin>>a>>b;
        rule[a]=b;
    }
    std::stack<std::string> chg;
    hashset<std::string> nums;
    nums.insert(n);
    chg.push(n);
    while(!chg.empty()){
        const auto cur=chg.top();
        chg.pop();
        for(int i=0;i<cur.size();++i){
            if(rule.contains(cur[i])){
                auto nxt=cur;
                nxt[i]=rule[cur[i]];
                if(!nums.contains(nxt)){
                    nums.insert(nxt);
                    chg.push(nxt);
                }
            }
        }
    }
    std::cout<<nums.size()<<std::endl;
}