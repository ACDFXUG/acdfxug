#include <iostream>
#include <vector>

//z+1=a,a-1=z
char move_char(char c,int64_t mv){
    if(mv>0){
        if(c+mv>'z') return c+mv-26;
        return c+mv;
    }else if(mv<0){
        if(c+mv<'a') return c+mv+26;
        return c+mv;
    }
    return c;
}

int main(){
    int n;
    std::cin>>n;
    std::string s(n,' ');
    std::cin>>s;
    int firstmove=(n&1)?-1:1;
    std::vector<int64_t> move(n),a(n);
    for(int i=0;i<n;std::cin>>a[i++]);
    for(int i=n-1;i>=0;--i){
        if(i==n-1)[[unlikely]] move[i]=firstmove*a[i];
        else move[i]=move[i+1]+firstmove*a[i];
        firstmove=-firstmove;
    }
    for(int i=0;i<n;i++){
        s[i]=move_char(s[i],move[i]%26);
    }
    std::cout<<s<<std::endl;
}