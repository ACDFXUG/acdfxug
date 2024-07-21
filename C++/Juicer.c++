#include <iostream>

int main(){
    int n,b,d,cnt=0;
    std::cin>>n>>b>>d;
    int *a=new int[n];
    for(int i=0;i<n;scanf("%d",a+i++));
    for(int t=0,i=0;i<n;i++){
        if(a[i]<=b){
            t+=a[i];
        }
        if(t>d){
            cnt++;
            t=0;
        }
    }
    std::cout<<cnt<<std::endl;
    delete[] a;
}