#include <iostream>
#define String std::string

int main(){
    int N,x=0,y=0;
    String T;
    std::cin>>N>>T;
    bool east=true,west=false,south=false,north=false;
    for(int i=0;i<N;i++){
        char action=T[i];
        if(action=='S'){
            if(east)x++;
            else if(west)x--;
            else if(north)y++;
            else if(south)y--;
        }else if(action=='R'){
            if(east)std::swap(east,south);
            else if(south)std::swap(south,west);
            else if(west)std::swap(west,north);
            else if(north)std::swap(north,east);
        }
    }
    printf("%d %d\n",x,y);
}