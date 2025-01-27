#include <stack>
#include <map>
#include <print>

template<class K,class V>
using tree_map=std::map<K,V>;

class MinStack{
private:
    std::stack<int> base;
    tree_map<int,int> val_cnt;
public:
    MinStack(){}
    void push(int val){
        base.push(val);
        val_cnt[val]++;
    }
    void pop(){
        int top=base.top();
        base.pop();
        val_cnt[top]==1?val_cnt.erase(top):val_cnt[top]--;
    }
    int top(){
        return base.top();
    }
    int getMin(){
        return val_cnt.begin()->first;
    }
};

int main(){
    MinStack *ms=new MinStack();
    ms->push(-2);
    ms->push(0);
    ms->push(-3);
    std::println("{}",ms->getMin());
    ms->pop();
    std::println("{}",ms->top());
    std::println("{}",ms->getMin());
    delete ms;
}