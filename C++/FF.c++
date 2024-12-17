#include <iostream>
#include <unordered_set>
#include <unordered_map>

template<typename T>
using HashSet=std::unordered_set<T>;

template<typename K,typename V>
using HashMap=std::unordered_map<K,V>;

int main(){
    int n,q;
    scanf("%d%d",&n,&q);
    HashMap<int,HashSet<int>> subscribe;
    for(int i=0,op,a,b;i<q;i++){
        scanf("%d%d%d",&op,&a,&b);
        switch(op){
            case 1:{
                subscribe[a].insert(b);
                break;
            }case 2:{
                subscribe[a].erase(b);
                break;
            }case 3:{
                bool subeach=subscribe[a].contains(b)
                    &&subscribe[b].contains(a);
                printf(subeach?"Yes\n":"No\n");
                break;
            }
        }
    }
}