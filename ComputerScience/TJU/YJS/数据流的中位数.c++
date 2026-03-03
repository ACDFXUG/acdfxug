#include <print>
#include <queue>

using namespace std;

struct MedianFinder{
    priority_queue<int> left;
    priority_queue<int,vector<int>,greater<int>> right;
    MedianFinder() {
        this->left=priority_queue<int>();
        this->right=priority_queue<int,vector<int>,greater<int>>();
    }
    
    void addNum(int num) {
        if(left.empty()||left.top()>num) left.push(num);
        else right.push(num);

        if(left.size()>right.size()+1) right.push(left.top()),left.pop();
        else if(right.size()>left.size()+1) left.push(right.top()),right.pop();
    }
    
    double findMedian() {
        if(left.size()==right.size()) return (left.top()+right.top())/2.00000;
        return left.size()>right.size()?left.top():right.top();
    }
};

int main(){
    MedianFinder mf;
    mf.addNum(1);
    mf.addNum(2);
    println("{}",mf.findMedian());
    mf.addNum(3);
    println("{}",mf.findMedian());
}
