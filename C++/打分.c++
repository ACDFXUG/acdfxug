#include <iostream>
#include <algorithm>
int main(){
    int n;
    for(;std::cin>>n&&n!=0;){
        int *score=new int[n];
        for(int i=0;i<n;i++){
            scanf("%d",&score[i]);
        }
        std::sort(score,score+n);
        double sum=0;
        for(int i=1;i<n-1;i++){
            sum+=score[i];
        }
        printf("%.2f\n",sum/(n-2));
    }
}