#include <iostream>
#define String std::string

bool isSimilarChar(char x,char y){
    if(x==y)return true;
    else if(x=='o'&&y=='0')return true;
    else if(y=='o'&&x=='0')return true;
    else if(x=='1'&&y=='l')return true;
    else if(y=='1'&&x=='l')return true;
    return false;
}

bool isSimilarString(String x,String y,int n){
    for(int i=0;i<n;i++){
        if(!isSimilarChar(x[i],y[i])){
            return false;
        }
    }
    return true;
}

int main(){
    int n;String s,t;
    std::cin>>n>>s>>t;
    printf(isSimilarString(s,t,n)?"Yes\n":"No\n");
}