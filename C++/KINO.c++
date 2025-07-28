#include <iostream>
#include <stack>

int group(std::string_view s){
    std::stack<int> stk{};
    int grp=0;
    for(const auto &c:s){
        switch(c){
            case 'S':{
                ++grp;
                break;
            }case 'L':{
                if(stk.empty()) stk.push(c);
                else{
                    stk.pop();
                    ++grp;
                }
                break;
            }
        }
    }
    return grp;
}

int main(){
    int N;
    std::cin>>N;
    std::string seat;
    seat.reserve(N);
    std::cin>>seat;
    int grp=group(seat);
    std::cout<<grp+1<<std::endl;
}
