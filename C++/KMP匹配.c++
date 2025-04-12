#include <iostream>
#include <vector>
#include <algorithm>

const std::pair<const int,const int>
KMP(std::string_view txt,std::string_view pat){
    const int n=txt.size(),m=pat.size();
    if(m==0) return {0,0};
    else if(n<m) return {-1,-1};
    std::vector<int> next(m);
    next[0]=-1;
    for(int i=1;i<m;i++){
        int j=next[i-1];
        while(j!=-1&&pat[i]!=pat[j+1]) j=next[j];
        if(pat[i]==pat[j+1]) j++;
        next[i]=j;
    }
    for(int i=0,j=-1;i<n;i++){
        while(j!=-1&&txt[i]!=pat[j+1]) j=next[j];
        if(txt[i]==pat[j+1]) j++;
        if(j==m-1) return {i-m+1,i+1};
    }
    return {-1,-1};
}

int main(){
    std::string txt("helloworld");
    std::string pat("lo");
    const auto &[l,r]=KMP(txt,pat);
    std::cout<<"["<<l<<", "<<r<<")"<<std::endl;
}