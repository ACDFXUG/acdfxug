#include <iostream>
#include <stack>
#include <vector>

bool isStackSequence(auto &&input,auto &&output){
    std::stack<int> s;
    int idx=0;
    for(const int &num:input){
        for(s.push(num);
            !s.empty()&&s.top()==output[idx];
        ++idx){
            s.pop();
        }
    }
    return s.empty();
}

int main(){
    int q;
    scanf("%d",&q);
    while(q--){
        int n;
        scanf("%d",&n);
        std::vector ipt(n,0),opt(n,0);
        for(int i=0;i<n;++i){
            scanf("%d",&ipt[i]);
        }
        for(int i=0;i<n;++i){
            scanf("%d",&opt[i]);
        }
        printf(isStackSequence(ipt,opt)?"Yes\n":"No\n");
    }
}