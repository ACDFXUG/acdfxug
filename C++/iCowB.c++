#include <iostream>
#include <map>
#define MAX(x,y) (x>y?x:y)
#define MIN(x,y) (x<y?x:y)

int main(){
    int N,T;
    scanf("%d %d",&N,&T);
    std::map<int,int> songs;
    int *ans=new int[T];
    for(int i=1,Ri;i<=N;i++){
        scanf("%d",&Ri);
        songs[i]=Ri;
    }
    for(int s=0;s<T;s++){
        int index=0x7fffffff,val=-1;
        for(auto &[_,value]:songs){
            val=MAX(val,value);
        }
        for(auto &[key,value]:songs){
            if(value==val){
                index=MIN(index,key);
            }
        }
        ans[s]=index;
        int Ri=songs[index],r=Ri%(N-1),n=Ri/(N-1);
        for(int i=1;i<=N;i++){
            if(i!=index){
                songs[i]+=n;
            }else{
                songs[i]=0;
            }
        }
        if(r!=0){
            for(int i=1;i<=r;){
                if(i!=index){
                    songs[i++]+=1;
                }
            }
        }
    }
    for(int i=0;i<T;i++){
        printf("%d\n",ans[i]);
    }
    delete[] ans;
}