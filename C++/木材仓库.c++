#include <iostream>
#include <unordered_set>
using namespace std;

int main(){
    int n;
    scanf("%d", &n);
    unordered_set<int> wood;
    while(n-->0){
        int act,length;
        scanf("%d%d",&act,&length);
        if(act==1){
            if(wood.contains(length)){
                printf("Already Exist\n");
            }else{
                wood.insert(length);
            }
        }else if(act==2){
            if(wood.contains(length)){
                printf("%d\n",length);
                wood.erase(length);
            }else{
                if(wood.empty()){
                    printf("Empty\n");
                    continue;
                }
                for(int k=1;k<length;k++){
                    int l1=length-k,l2=length+k;
                    if(wood.contains(l1)&&wood.contains(l2)){
                        printf("%d\n",l1);
                        wood.erase(l1);
                        break;
                    }else if(wood.contains(l1)&&!wood.contains(l2)){
                        printf("%d\n",l1);
                        wood.erase(l1);
                        break;
                    }else if((!wood.contains(l1))&&wood.contains(l2)){
                        printf("%d\n",l2);
                        wood.erase(l2);
                        break;
                    }else{
                        continue;
                    }
                }
            }
        }
    }
}