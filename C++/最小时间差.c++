#include <iostream>
#include <algorithm>
#include <vector>

struct Time{
    int h,m;
    Time(int hh=0,int mm=0):h(hh),m(mm){}
    Time(std::string time/*"HH:MM"*/):
    h(std::stoi(time.substr(0,2))),m(std::stoi(time.substr(3,2))){}
    Time(const Time &t):Time(t.h,t.m){}
    Time(Time &&t):Time(std::move(t.h),std::move(t.m)){}
    Time &operator =(const Time &t){
        h=t.h;
        m=t.m;
        return *this;
    }
    Time &operator =(Time &&t){
        h=std::move(t.h);
        m=std::move(t.m);
        return *this;
    }
    bool operator ==(const Time &t) const{
        return h==t.h&&m==t.m;
    }
    bool operator <(const Time &t) const{
        return h<t.h||(h==t.h&&m<t.m);
    }
    Time operator -(const Time &t) const{
        Time t1(h-t.h,m-t.m);
        if(t1.m<0){
            t1.m+=60;
            --t1.h;
        }
        if(t1.h<0){
            t1.h+=24;
        }
        return t1;
    }
    int to_minute() const{
        return h*60+m;
    }
};

int findMinDifference(std::vector<std::string>& timePoints) {
    std::vector<Time> times;
    for(times.reserve(timePoints.size()+1);const auto &time:timePoints){
        times.emplace_back(time);
    }
    std::sort(times.begin(),times.end());
    times.emplace_back(times.front());
    Time min_t{24,60};
    for(int i=0;i<times.size()-1;++i){
        min_t=std::min(min_t,times[i+1]-times[i]);
    }
    return min_t.to_minute();
}

int main(){
    std::vector<std::string> timePoints{"01:01","02:01","03:00","03:01"};
    std::cout<<findMinDifference(timePoints)<<std::endl;
}