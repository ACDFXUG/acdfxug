#include <iostream>
#include <string>
#include <algorithm>

int main(){
    int N,Q;
    scanf("%d%d",&N,&Q);
    std::string str;
    std::cin>>str;
    for(int i=0,act,x;i<Q;i++){
        scanf("%d%d",&act,&x);
        switch(act){
            case 1:{
                std::reverse(str.begin(),str.begin()+(N-x));
                std::reverse(str.begin()+(N-x),str.end());
                std::reverse(str.begin(),str.end());
                break;
            }case 2:{
                printf("%c\n",str[x-1]);
                break;
            }
        }
    }
}