#include <iostream>
#include <vector>
#include <algorithm>

int main(){
    int n,sum=0;
    std::cin>>n;
    std::vector<int> odd;
    odd.reserve(n);
    for(int i=0,num;i<n;i++){
        scanf("%d",&num);
        sum+=num;
        if(num&1){
            odd.push_back(num);
        }
    }
    std::sort(odd.begin(),odd.end(),std::greater<int>());
    LOOP:
    if(!(sum&1)&&!!sum){
        printf("%d\n",sum);
    }else{
        if(!odd.size()){
            printf("NIESTETY\n");
            goto END;
        }
        sum-=odd.back();
        odd.pop_back();
        goto LOOP;
    }
    END:
    return 0;
}