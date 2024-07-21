#include <iostream>
#define String std::string
#define input std::cin>>

int main(){
    int S,t=0;
    scanf("%d",&S);
    String init="";
    for(;S>0;S--){
        char act;input act;
        if(act=='A'){
            char position;
            input position;
            t++;
            if(position=='L'){
                init=std::to_string(t)+init;
            }else if(position=='R'){
                init=init.append(std::to_string(t));
            }
        }else if(act=='D'){
            char position;
            input position;
            if(position=='L'){
                int k;
                scanf("%d",&k);
                init=init.substr(k);
            }else if (position=='R'){
                int k;scanf("%d",&k);
                int l=init.length();
                init=init.substr(0,l-k);
            }  
        }
    }
    for(char x:init){
        printf("%c\n",x);
    }
}