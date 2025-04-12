#include <iostream>
#include <set>

struct LUPrefix{
    int size;
    std::set<int> prefix;
    LUPrefix(int n):size(n){
        for(int i=1;i<=n;this->prefix.insert(i++));
    }
    void upload(int video) {
        prefix.erase(video);
    }
    int longest() {
        if(*prefix.begin()==1){
            return 0;
        }else if(prefix.empty()){
            return size;
        }else{
            return *prefix.begin()-1;
        }
    }
};

int main(){
    LUPrefix* lup = new LUPrefix(4);
    lup->upload(3);
    std::cout<<lup->longest();
    lup->upload(1);
    std::cout<<lup->longest();
    lup->upload(2);
    std::cout<<lup->longest();
    return 0;
}