#include <iostream>
#define PRINTLN(x) std::cout<< (x) <<std::endl

class ParkingSystem {
private:
    int big,medium,small;
public:
    ParkingSystem(int big, int medium, int small) {
        this->big=big;
        this->medium=medium;
        this->small=small;
    }
    
    bool addCar(int carType) {
        switch(carType){
            case 1:{
                if(big>0){
                    --big;
                    return true;
                }else{
                    return false;
                }
            }
            case 2:{
                if(medium>0){
                    --medium;
                    return true;
                }else{
                    return false;
                }
            }
            case 3:{
                if(small>0){
                    --small;
                    return true;
                }else{
                    return false;
                }
            }
            default:throw;
        }
    }
};

int main(){
    auto ps=new ParkingSystem(1,1,0);
    PRINTLN(ps->addCar(1));
    PRINTLN(ps->addCar(2));
    PRINTLN(ps->addCar(3));
    PRINTLN(ps->addCar(1));
}