#include <iostream>
#include <tuple>

int digitSum(int n){
    int ans=0;
    for(;n>0;n/=10){
        ans+=n%10;
    }
    return ans;
}

std::tuple<int,bool> isValid(std::string s){
    for(int i=s.length()-2;i>=0;i-=2){
        s[i]=char('0'+digitSum((s[i]-'0')<<1));
    }
    int ans=0;
    for(int i=s.length()-1;i>=0;i--){
        ans+=(s[i]-'0');
    }
    int last=(ans*9)%10;
    printf("%c\n",s.back());
    return {ans,last==s.back()-'0'};
}

int main(){
    int l;
    std::string s;
    std::cin>>l>>s;
    int i;
    for(i=0;i<s.length();i++){
        if(s[i]=='x'){
            break;
        }
    }
    for(char c='0';c<='9';c++){
        s[i]=c;
        auto &&[ans,valid]=isValid(s);
        printf("%d %d\n",ans,valid);
        if(valid){
            printf("%s\n",s.c_str());
            printf("%c\n",c);
            break;
        }
    }
}