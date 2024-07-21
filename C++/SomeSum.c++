#include <iostream>

int digitSum(int n){
    int ans=0;
    for(char x:std::to_string(n)){
        ans+=x-'0';
    }
    return ans;
}

int main(){
    int N,A,B,t=0;
    std::cin>>N>>A>>B;
    for(int i=1;i<=N;i++){
        int sum=digitSum(i);
        if(sum>=A&&sum<=B){
            t+=i;
        }
    }
    std::cout<<t<<std::endl;
}