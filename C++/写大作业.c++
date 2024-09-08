#include <iostream>
#include <unordered_map>
#include <array>

std::array<int,26> get_letters(std::string &str){
    std::array<int,26> letters{};
    for(char &c:str){
        letters[c-'a']++;
    }
    return letters;
}

bool is_similar(std::string &s1,std::string &s2){
    std::array<int,26> letters1=get_letters(s1);
    std::array<int,26> letters2=get_letters(s2);
    for(int i=0;i<26;i++){
        if(letters1[i]!=letters2[i])
            return false;
    }
    return true;
}

int main(){
    int n,q;
    std::cin>>n>>q;
    std::unordered_map<int,std::string> s;
    for(int i=1;i<=n;i++){
        std::string str;
        std::cin>>str;
        s[i]=str;
    }
    while(q-->0){
        int op,x,y;
        scanf("%d %d %d",&op,&x,&y);
        switch(op){
            case 1:{
                s[y]+=s[x];
                s.erase(x);
                break;
            }case 2:{
                printf(is_similar(s[x],s[y])?"Yes\n":"No\n");
            }
        }
    }
}