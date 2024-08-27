#include <iostream>
#include <map>

int main(){
    int N;
    std::cin>>N;
    std::map<int,int> num_locat;
    for(int i=1,num;i<=N;i++){
        scanf("%d",&num);
        if(!num_locat.contains(num)){
            num_locat[num]=i;
        }
    }
    printf("%d\n",num_locat.rbegin()->second);
}