#include <iostream>
#include <unordered_map>

int main(){
    int t;
    std::cin>>t;
    while(t-->0){
        int n,head=1;
        scanf("%d",&n);
        std::unordered_map<int,int> queue;
        queue[1]=head;
        for(int i=2;i<=n;i++){
            if((i&1)==0){
                head&=i;
                queue[i]=head;
            }else{
                head^=i;
                queue[i]=head;
            }
        }
        printf("%d\n",queue[n]);
    }
}