#include <iostream>
#include <bitset>

static std::bitset<0x80000000> bs;
int main(){
    int L,R;
    std::cin>>L>>R;
    bs.set();
    bs[0]=bs[1]=0;
    for(int i=2;i*i<=R;i++){
        if(bs[i])[[likely]]{
            for(int j=i*i;j<=R;j+=i){
                bs[j]=0;
            }
        }
    }
    int cnt=0;
    for(int i=L;i<=R;i++){
        cnt+=bs[i];
    }
    std::cout<<cnt<<std::endl;
}