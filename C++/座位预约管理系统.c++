#include <iostream>
#include <set>
#include <unordered_set>

class SeatManager{
private:
    std::set<int> free;
    std::unordered_set<int> taken;
public:
    SeatManager(int n){
        for(int i=1;i<=n;i++){
            free.insert(i);
        }
    }
    int reserve(){
        int seat=*free.begin();
        free.erase(free.begin());
        taken.insert(seat);
        return seat;
    }
    void unreserve(int seatNumber) {
        taken.erase(seatNumber);
        free.insert(seatNumber);
    }
};