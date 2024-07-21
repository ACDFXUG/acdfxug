#include <iostream>

int main(){
    int n,ch[4],t=0;
    scanf("%d",&n);
    for(char p;n--;){
        std::cin>>p;
        switch(p){
            case 'P':ch[0]++;
            break;
            case 'W':ch[1]++;
            break;
            case 'G':ch[2]++;
            break;
            case 'Y':ch[3]++;
            break;
        }
    }
    for(int x:ch){
        if(x>0)t++;
    }
    switch(t){
        case 3:printf("Three");
        break;
        case 4:printf("Four");
        break;
    }
}