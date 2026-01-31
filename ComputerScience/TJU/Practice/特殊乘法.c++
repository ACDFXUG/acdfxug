#include <iostream>

int main(){
    std::string a,b;
    std::cin >> a >> b;
    int ans=0;
    for(int i = 0;i < a.length();i++){
        for(int j=0;j < b.length();j++){
            ans+=(a[i]-'0')*(b[j]-'0');
        }
    }
    std::cout << ans << std::endl;
}