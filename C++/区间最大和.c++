#include <iostream>

int main(){
    int n,m;
    std::cin>>n>>m;
    int *num=new int[n];
    for(int i=0;i<n;std::cin>>num[i++]);
    int left{},sum{},max_sum{},i{},j{};
    for(int right=0;right<n;++right){
        sum+=num[right];
        while(sum>m){
            sum-=num[left++];
        }
        if(sum>max_sum){
            max_sum=sum;
            i=left;
            j=right;
        }
    }
    std::cout<<i+1<<" "<<j+1<<" "<<max_sum<<std::endl;
    delete[] num;
}