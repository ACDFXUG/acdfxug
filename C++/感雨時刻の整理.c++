#include <iostream>
#include <algorithm>
#include <vector>

struct Time{
    short h,m;
    bool operator ==(const Time &t) const{
        return h==t.h&&m==t.m;
    }
    bool operator <(const Time &t) const{
        return h==t.h?m<t.m:h<t.h;
    }
};

struct Period{
    Time start,end;
    bool operator &&(const Period &p){
        auto maxs=std::max(start,p.start);
        auto mine=std::min(end,p.end);
        return maxs<mine||maxs==mine;
    }
    Period operator |(const Period &p){
        return {std::min(start,p.start),std::max(end,p.end)};
    }
    bool operator <(const Period &p){
        return start<p.start||(start==p.start&&end<p.end);
    }
};