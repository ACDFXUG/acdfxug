#include <iostream>
#include <unordered_map>
#include <set>
#define println(x) std::cout<<(x)<<std::endl;

class NumberContainers{
private:
    std::unordered_map<int,int> loc;  //[index,value]
    std::unordered_map<int,std::set<int>> idx;  //[value,[index]]
public:
    NumberContainers(){
        this->loc={};
        this->idx={};
    }
    void change(int index, int number){
        if(loc.contains(index)){
            int before=loc[index];
            loc[index]=number;
            idx[before].erase(index);
            if(idx[before].empty()){
                idx.erase(before);
            }
            idx[number].insert(index);
        }else{
            loc[index]=number;
            idx[number].insert(index);
        }
    }
    int find(int number) {
        return idx.contains(number)?
        *idx[number].begin():-1;
    }
};

int main(){
    NumberContainers *nc=new NumberContainers();
    nc->change(1,10);
    println(nc->find(10));
    nc->change(1,20);
    println(nc->find(10));
    println(nc->find(20));
    println(nc->find(30));
}