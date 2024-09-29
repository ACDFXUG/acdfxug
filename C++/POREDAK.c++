#include <iostream>
#include <unordered_map>

int main(){
    int n,sum=0;
    std::cin>>n;
    std::unordered_map<std::string,int> index;
    for(int i=0;i<n;i++){
        std::string s;
        std::cin>>s;
        index[s]=i;
    }
    std::string *arr=new std::string[n];
    for(int i=0;i<n;std::cin>>arr[i++]);
    for(int i=0;i<n-1;i++){
        for(int j=i+1;j<n;j++){
            if(index[arr[i]]<index[arr[j]]){
                sum++;
            }
        }
    }
    delete[] arr;
    printf("%d/%d\n",sum,n*(n-1)>>1);
}