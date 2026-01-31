#include <iostream>
#include <vector>

int xorSum(const std::vector<int> &A){
    int ans=0;
    const int n=A.size();
    for(int i=0;i<n;++i){
        for(int j=i+1;j<n;++j){
            ans+=A[i]^A[j];
        }
    }
    return ans;
}

int main(){
    int T;
    std::cin>>T;
    for(int n;T-->0;){
        std::cin>>n;
        std::vector<int> A(n);
        for(int i=0;i<n;++i){
            std::cin>>A[i];
        }
        std::cout<<xorSum(A)<<std::endl;
    }
}