#include <iostream>
#include <queue>

class MyStack{
    std::queue<int> main,tmp;
public:
    MyStack(){}
    void push(int x) {
        main.push(x);
    }
    int pop(){
        printf("%d %d\n",main.size(),tmp.size());
        for(;main.size()>1;main.pop()){
            tmp.push(main.front());
        }
        const int &ans=main.front();
        main.pop();
        for(;!tmp.empty();tmp.pop()){
            main.push(tmp.front());
        }
        return ans;
    }
    int top() {
        printf("%d %d\n",main.size(),tmp.size());
        for(;main.size()>1;main.pop()){
            tmp.push(main.front());
        }
        const int &ans=main.front();
        main.pop();
        tmp.push(ans);
        for(;!tmp.empty();tmp.pop()){
            main.push(tmp.front());
        }
        return ans;
    }
    bool empty() {
        return main.empty();
    }
};

int main(){
    MyStack *ms=new MyStack();
    ms->push(1);
    ms->push(2);
    printf("%d\n",ms->top());
    printf("%d\n",ms->pop());
    printf("%d\n",ms->pop());
    printf("%d\n",ms->empty());
}