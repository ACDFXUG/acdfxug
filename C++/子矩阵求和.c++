#include<iostream>
using namespace std;
int max(int x,int y){
    if(x>y){
        return x;
    }else{
        return y;
    }
}
int min(int x,int y){
    if(x>y){
        return y;
    }else{
        return x;
    }
}
int main()
{
    int M,N,Q;
    cin>>M>>N>>Q;
    int p[M][N];
    for(int i=0;i<M;i++){
        for(int j=0;j<N;j++){
            cin>>p[i][j];
        }
    }
    for(int s=0;s<Q;s++){
        int x1,x2,y1,y2;
        cin>>x1>>y1>>x2>>y2;
        int sum=0;
        for(int i=min(x1,x2);i<=max(x1,x2);i++){
            for(int j=min(y1,y2);j<=max(y1,y2);j++){
                sum+=p[i][j];
            }
        }
        cout<<sum<<endl;
    }
}