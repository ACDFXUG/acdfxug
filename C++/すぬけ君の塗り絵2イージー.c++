#include <iostream>

int main(){
    int W,H,N;
    scanf("%d%d%d",&W,&H,&N);
    bool **plane=new bool *[H];
    for(int i=0;i<H;plane[i++]=new bool[W](true));
    for(int i=0;i<N;i++){
        int x,y,a;
        scanf("%d%d%d",&x,&y,&a);
        switch(a){
            case 1:{
                for(int i=0;i<H;i++){
                    for(int j=0;j<x;j++){
                        plane[i][j]=false;
                    }
                }
                break;
            }case 2:{
                for(int i=0;i<H;i++){
                    for(int j=x;j<W;j++){
                        plane[i][j]=false;
                    }
                }
                break;
            }case 3:{
                for(int i=0;i<y;i++){
                    for(int j=0;j<W;j++){
                        plane[i][j]=false;
                    }
                }
                break;
            }case 4:{
                for(int i=y;i<H;i++){
                    for(int j=0;j<W;j++){
                        plane[i][j]=false;
                    }
                }
                break;
            }
        }
    }
    int ans=0;
    for(int i=0;i<H;i++){
        for(int j=0;j<W;j++){
            ans+=plane[i][j];
        }
    }
    printf("%d\n",ans);
}