#include <iostream>

int $2009[]{
    31,28,31,30,
    31,30,31,31,
    30,31,30,31
};

std::string week[]{
    "Monday","Tuesday","Wednesday",
    "Thursday","Friday","Saturday",
    "Sunday",
};

int main(){
    int day,month;
    std::cin>>day>>month;
    for(int i=0;i<month-1;i++){
        day+=$2009[i];
    } 
    std::cout<<week[(day+2)%7]<<std::endl;
}