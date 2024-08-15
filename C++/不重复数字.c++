#include <iostream>
#include <vector>
#include <unordered_set>

int main(){
    int T;
    std::cin>>T;
    while(T-->0){
        int n;
        scanf("%d",&n);
        std::vector<int> nums;
        std::unordered_set<int> set;
        for(int i=0,x;i<n;i++){
            scanf("%d",&x);
            if(!set.contains(x)){
                nums.push_back(x);
                set.insert(x);
            }
        }
        for(int &x:nums){
            printf("%d ",x);
        }
        printf("\n");
    }
}
