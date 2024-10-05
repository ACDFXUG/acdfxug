#include <iostream>
#include <cmath>

double angleClock(int hour, int minutes) {
    double theta1=30*(hour%12)+minutes*30.0/60;
    double theta2=6*minutes;
    double abs1=fabs(theta1-theta2);
    return fmin(360.0-abs1,abs1);
}

int main(){
    std::cout<<angleClock(1,57)<<std::endl;
}