#include <iostream>
#include <unordered_map>
using namespace std;

int numberOfSpecialChars(string word) {
    unordered_map<char,int> char_index;
    int ans=0;
    for(int i=0,l=word.length();i<l;i++){
        char c=word[i];
        if(c>='a'&&c<='z'){
            char_index[c]=max(char_index[c],i);
        }else{
            if(char_index.contains(c)){
                char_index[c]=min(char_index[c],i);
            }else{
                char_index[c]=i;
            }
        }
    }
    for(char c='a';c<='z';c++){
        if(char_index.contains(c)&&char_index.contains(c-'a'+'A')){
            if(char_index[c]<char_index[c-'a'+'A']){
                ans++;
            }
        }
    }
    return ans;
}

int main(){
    string word="cCceDC";
    cout<<numberOfSpecialChars(word)<<endl;
}