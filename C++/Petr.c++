#include <iostream>

int main(){
    int page,read[7],Read[7],t=0;
    std::cin>>page;
    for(int i=0;i<7;i++){
        scanf("%d",&read[i]);
        t+=read[i];
        Read[i]=t;
    }
    int week=page/t,remain=page-week*t;
    if(remain==0){
        int last=page-(week-1)*t;
        for(int i=0;i<7;i++){
            if(Read[i]==last){
                printf("%d\n",i+1);
                return 0;
            }
        }
    }
    for(int i=0;i<7;i++){
        if(remain==Read[i]){
            printf("%d\n",i+1);
            return 0;
        }
        if(i<6){
            if(remain>Read[i]&&remain<Read[i+1]){
                printf("%d\n",i+2);
                return 0;
            }
        }
    }
}