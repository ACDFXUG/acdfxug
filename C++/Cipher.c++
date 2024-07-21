#include <iostream>
#include <deque>
#define String std::string


int main(){
    String s,t="";
    std::cin>>s;
    int l=s.length();
    std::deque<char> a;
    for(int i=0;i<l;i++){
        a.push_back(s[i]);
    }
    if(l&1){
        for(;;){
            if(a.size()>0){
                t=a.front()+t;
                a.pop_front(); 
            }else{
                break;
            }
            if(a.size()>0){
                t=a.back()+t;
                a.pop_back();
            }else{
                break;
            }
        }
    }else{
        for(;;){
            if(a.size()>0){
                t=a.back()+t;
                a.pop_back(); 
            }else{
                break;
            }
            if(a.size()>0){
                t=a.front()+t;
                a.pop_front();
            }else{
                break;
            } 
        }
    }
    printf("%s\n",t.data());
}