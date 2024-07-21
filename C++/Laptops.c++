#include <iostream>

struct laptop{
    int value,quality;
}LAPTOP;

int main(){
    int n,nothas=1;
    std::cin>>n;
    laptop *lp=new laptop[n];
    for(int i=0;i<n;i++){
        scanf("%d%d",&lp[i].value,&lp[i].quality);
    }
    for(int i=0;i<n-1;i++){
        for(int j=1;j<n;j++){
            if((lp[i].value>lp[j].value&&lp[i].quality<lp[j].quality)
            ||(lp[i].value<lp[j].value&&lp[i].quality>lp[j].quality)){
                printf("Happy Alex\n");
                nothas=0;
                goto p;
            }
        }
    }
    p:
    if(nothas){
        printf("Poor Alex\n");
    }
    delete[] lp;
    
}