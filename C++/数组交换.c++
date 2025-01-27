#include <iostream>

int main(){
    int n,m,q;
    scanf("%d%d%d",&n,&m,&q);
    char **arr=new char *[n];
    for(int i=0;i<n;i++){
        arr[i]=new char[m+1]('\0');
        scanf("%s",arr[i]);
    }
    for(;q-->0;){
        int act,x,y;
        scanf("%d%d%d",&act,&x,&y);
        switch(act){
            case 1:{
                if(x!=y) 
                    std::swap(arr[x-1],arr[y-1]);
                break;
            }case 2:{
                if(x!=y)
                    for(int i=0;i<n;i++){
                        std::swap(arr[i][x-1],arr[i][y-1]);
                    }
                break;
            }case 3:{
                printf("%c\n",arr[x-1][y-1]);
                break;
            }
        }
    }
    for(int i=0;i<n;i++){
        printf("%s\n",arr[i]);
        delete[] arr[i];
    }
    delete[] arr;
}