#include <iostream>
#include <vector>
#define Vector std::vector<int>

bool isRepeat(Vector a,int x){
    if(!a.empty()){
        for(int p:a){
            if(p==x){
                return true;
            }
        } 
        return false;
    }else{
        return false;
    }
}

int main(){
    int n;Vector a;
    std::cin>>n;
    for(int x;n--;){
        scanf("%d",&x);
        if(!isRepeat(a,x)){
            a.push_back(x);
        }
    }
    for(int i=0,l=a.size();i<l;i++){
        printf(i==l-1?"%d\n":"%d ",a[i]);
    }
    
}