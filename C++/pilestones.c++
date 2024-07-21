#include <iostream>
#define String std::string

int main(){
    int n;
    scanf("%d",&n);
    String s;
    std::cin>>s;
    for(int i=0;;i++){
        int t=0;
        for(char x:s){
            if(x=='-'){
                t--;
                if(t+i<0){
                    break;
                }
            }else{
                t++;
                if(t+i<0){
                    break;
                }
            }
        }
        if(i+t>=0){
            printf("%d\n",i+t);
            break;
        }
    }
}