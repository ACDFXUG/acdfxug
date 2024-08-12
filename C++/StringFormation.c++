#include <iostream>
#include <cstring>
#include <algorithm>

int main(){
    std::string S;
    std::cin>>S;
    int Q;
    scanf("%d",&Q);
    while(Q-->0){
        int Ti;
        scanf("%d",&Ti);
        if(Ti==1){
            std::reverse(S.begin(),S.end());
        }else if(Ti==2){
            int Fi;
            std::string Ci;
            scanf("%d",&Fi);
            std::cin>>Ci;
            if(Fi==1){
                S.insert(0,Ci);
            }else if(Fi==2){
                S.append(Ci);
            }
        }
    }
    std::cout<<S<<std::endl;
}