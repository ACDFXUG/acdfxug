#include <iostream>
#include <queue>

#define println(x) std::cout << (x) << std::endl

class RecentCounter{
private:
    std::queue<int> time;
public:
    RecentCounter(){
        this->time={};
    }
    int ping(int t){
        for(time.push(t);
        time.front()<t-3000;
        time.pop());
        return time.size();
    }
};

int main(){
    RecentCounter *rc=new RecentCounter();
    println(rc->ping(1));
    println(rc->ping(100));
    println(rc->ping(3001));
    println(rc->ping(3002));
}