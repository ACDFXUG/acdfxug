#include <iostream>

int romanToInt(std::string_view s){
    int ans=0;
    for(int i=0;i<s.size();i++){
        if(s[i]=='I'){
            ans+=1;
        }
        else if(s[i]=='V'){
            ans+=5;
        }
        else if(s[i]=='X'){
            ans+=10;
        }
        else if(s[i]=='L'){
            ans+=50;
        }
        else if(s[i]=='C'){
            ans+=100;
        }
        else if(s[i]=='D'){
            ans+=500;
        }
        else if(s[i]=='M'){
            ans+=1000;
        }
    }
    for(int i=0;i<s.size()-1;i++){
        if(s[i]=='I'){
            if(s[i+1]=='V'||s[i+1]=='X'){
                ans-=2;
            }
        }
        else if(s[i]=='X'){
            if(s[i+1]=='L'||s[i+1]=='C'){
                ans-=20;
            }
        }
        else if(s[i]=='C'){
            if(s[i+1]=='D'||s[i+1]=='M'){
                ans-=200;
            }
        }
    }
    return ans;
}

int main(){
    int a=romanToInt("LVIII");
    std::cout<<a<<std::endl;
}