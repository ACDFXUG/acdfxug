#include <iostream>
#include <vector>
#include <algorithm>
typedef std::string String;

int main(){
    int N;
    scanf("%d",&N);
    String *words=new String[N];
    for(int i=0;i<N;i++){
        String str;
        std::cin>>str;
        words[i]=str;
    }
    String T;
    std::cin>>T;
    std::vector<String> ans;
    for(int i=0;i<N;i++){
        String t=words[i];
        if(t.starts_with(T)){
            ans.push_back(t);
        }
    }
    std::sort(ans.begin(),ans.end());
    for(String &x:ans){
        printf((x+"\n").data());
    }
    delete[] words;
}