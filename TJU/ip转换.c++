#include <iostream>
#include <cmath>
typedef std::string String;
int toDec(String bin){
    int dec = 0;
    for(int i = 0; i < bin.length(); i++){
        if(bin[i] == '1'){
            dec += std::pow(2, bin.length() - i - 1);
        }
    }
    return dec;
}
int main(){
    int n;
    std::cin>>n;
    for(;n-->0;){
        String ip_bin;
        std::cin>>ip_bin;
        int a=toDec(ip_bin.substr(0,8));
        int b=toDec(ip_bin.substr(8,8));
        int c=toDec(ip_bin.substr(16,8));
        int d=toDec(ip_bin.substr(24,8));
        printf("%d.%d.%d.%d\n",a,b,c,d);
    }
}