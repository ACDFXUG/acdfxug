#include <print>
#include <vector>

using namespace std;

struct Interval{
    int start,end;
    Interval(int s=0,int e=0):start(s),end(e){}
    Interval(Interval &&inter)=default;
    Interval(const Interval &inter)=default;

    Interval &operator =(const Interval &inter)=default;
    Interval &operator =(Interval &&inter)=default;

    bool operator <(const Interval &inter) const{
        return start<inter.start;
    }

    bool operator ==(const Interval &inter) const{
        return start==inter.start&&end==inter.end;
    }

    bool operator ^(const Interval &inter) const{
        int m=max(start,inter.start);
        int M=min(end,inter.end);
        return m<=M;
    }

    Interval operator +(const Interval &inter) const{
        if(*this^inter){
            return {min(start,inter.start),max(end,inter.end)};
        }else{
            throw "error";
        }
    }
};

// 二分查找最近的区间
int binary_search(const vector<Interval> &intervals,const Interval &NEW){ 
    int left=0,right=intervals.size()-1;
    while(left<=right){
        int mid=(left+right)/2;
        if(intervals[mid]==NEW) return mid;
        else if(intervals[mid]<NEW) left=mid+1;
        else right=mid-1;
    }
    return left;
}

vector<vector<int>> insert(vector<vector<int>>& intervals, vector<int>& newInterval) {
    if(intervals.empty()) return {{newInterval[0],newInterval[1]}};
    
    vector<Interval> inters;
    Interval NEW{newInterval[0],newInterval[1]};
    
    for(auto &inter:intervals) inters.emplace_back(inter[0],inter[1]);
    if(intervals.size()==1){
        if(NEW^inters[0]){
            inters[0]=NEW+inters[0];
        }else{
            if(inters[0]<NEW) inters.push_back(NEW);
            else inters.insert(inters.begin(),NEW);
        }
    }else{
        int index=binary_search(inters,NEW)-1;
        for(int i=index;i<inters.size();++i){
            if(inters[i]^NEW){
                NEW=inters[i]+NEW;
                inters.erase(inters.begin()+i);
                --i;
            }else{
                inters.insert(inters.begin()+i,NEW);
                break;
            }
        }
    }
    vector<vector<int>> ans;
    for(const auto &[start,end]:inters){
        ans.push_back(vector<int>{start,end});
    }
    return ans;
}

int main(){
    vector<vector<int>> intervals{{1,2},{3,5},{6,7},{8,10},{12,16}};
    vector<int> newInterval{4,8};
    println("{}",insert(intervals,newInterval));
}