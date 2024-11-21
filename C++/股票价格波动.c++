#include <iostream>
#include <set>
#include <unordered_map>

class StockPrice{
private:
    std::set<int> prices;
    std::unordered_map<int,int> time;
    int cur_time;
    int cur_price;
public:
    StockPrice(){
        cur_time=0;
    }
    void update(int timestamp, int price) {
        if(timestamp>=cur_time){
            cur_price=price;
            cur_time=timestamp;
        }
        if(time.contains(timestamp)){
            prices.erase(time[timestamp]);
        }
        prices.insert(price);
        time[timestamp]=price;
    }
    int current() {
        return cur_price;
    }
    int maximum() {
        return *prices.rbegin();
    }
    int minimum() {
        return *prices.begin();
    }
};

int main(){
    StockPrice *sp=new StockPrice();
    sp->update(1,10);
    sp->update(2,5);
    std::cout<<sp->current()<<std::endl;
    std::cout<<sp->maximum()<<std::endl;
    sp->update(1,3);
    std::cout<<sp->maximum()<<std::endl;
    sp->update(4,2);
    std::cout<<sp->minimum()<<std::endl;
}