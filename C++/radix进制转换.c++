#include <iostream>
#define String std::string
#define large __int128_t
String RADIX="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

large power(int a,int x){
    large ans=1;
    for(;x--;ans*=a);
    return ans;
}

large parseUll(String str,int radix){
    large parse=0ull;
    int l=str.length();
    for(int i=0;i<l;i++){
        parse+=RADIX.find(str[i])*power(radix,l-i-1);
    }
    return parse;
}

String toString(large str,int radix){
    String ans="";
    for(large m=str;m>0;m/=radix){
        ans=RADIX[m%radix]+ans;
    }
    return ans==""?"0":ans;
}

String toString(String str,int r,int R){  //str base r=>str base R
    return toString(parseUll(str,r),R);
}

int main(){
    int N,R;
    std::cin>>N>>R;
    std::cout<< toString(N,R) <<std::endl;
}

