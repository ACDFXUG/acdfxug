#include <iostream>
#include <ctime>
#include <time.h>

class Time{
    public:
    int hour, minute, second;
    Time(){}
    Time(int hour, int minute, int second){
        this->hour = hour;
        this->minute = minute;
        this->second = second;
    }
    ~Time(){}
    void Set(int hours,int minutes,int seconds){
        hour = hours;
        minute = minutes;
        second = seconds;
    }

    void increment(){
        second++;
    }

    void Write() const{
        printf("%02d:%02d:%02d\n",hour,minute,second);
    }

    bool Equal(Time otherTime) const{
        return (hour==otherTime.hour && minute==otherTime.minute && second==otherTime.second);
    }

    bool LessThan(Time otherTime) const{
        return (hour<otherTime.hour || (hour==otherTime.hour && minute<otherTime.minute) || (hour==otherTime.hour && minute==otherTime.minute && second<otherTime.second));
    }
};