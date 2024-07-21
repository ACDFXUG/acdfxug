#include <iostream>
#include <algorithm>
#define toString std::to_string
#define charToInt(Integer) (Integer-'0')
typedef std::string String;

String reverse(String a){
    std::reverse(a.begin(),a.end());
    return a;
}
String plus(String s1,String s2){
    int l1=s1.length(),l2=s2.length(),
    l=std::max(l1,l2),Δl=std::abs(l1-l2);
    String ans=String();
    if(l1<l2){std::swap(s1,s2);}
    s1=reverse(s1);s2=reverse(s2);
    s2+=String(Δl,'0');
    int a=charToInt(s1[0])+charToInt(s2[0]);
    ans+=toString(a%10);
    for(int i=1;i<l;i++){
        int b=charToInt(s1[i])+charToInt(s2[i]);
        a=b+=a/10;
        ans+=toString(a%10);
    }
    return reverse(ans.append(a>9?"1":""));
}

int main(){
    String a,b;
    std::cin>>a>>b;
    std::cout<< plus(a,b) <<std::endl;
}