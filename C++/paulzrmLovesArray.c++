#include <iostream>
#include <vector>
#include <algorithm>

int main(){
    int n,m;
    std::cin>>n>>m;
    std::vector<int> arr(n);
    for(int i=0;i<n;i++){
        arr[i]=i+1;
    }
    while(m-->0){
        int act;
        scanf("%d",&act);
        switch(act){
            case 1:{
                std::sort(arr.begin(),arr.end());
                break;
            }case 2:{
                std::sort(arr.rbegin(),arr.rend());
                break;
            }case 3:{
                int x,y;
                scanf("%d%d",&x,&y);
                std::swap(arr[x-1],arr[y-1]);
                break;
            }case 4:{
                std::reverse(arr.begin(),arr.end());
            }
        }
    }
    for(int &x:arr){
        printf("%d ",x);
    }
}