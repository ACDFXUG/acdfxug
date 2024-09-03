#include <iostream>

std::string convertToTitle(int columnNumber) {
    std::string ans="";
    while(columnNumber){
        int temp=columnNumber%26;
        if(temp==0) temp=26;
        ans+='A'+temp-1;
        columnNumber=(columnNumber-temp)/26;
    }
    return std::string(ans.rbegin(),ans.rend());
}

int main(){
    std::cout<<convertToTitle(2147483647);
    return 0;
}