#include <iostream>

struct Bit{
    bool val:1;
    Bit(bool &&x=1):val(x){}
    explicit operator bool() const{
        return val;
    }
    Bit &operator =(bool &&b){
        val=b;
        return *this;
    }
    Bit &operator =(const Bit &b){
        val=b.val;
        return *this;
    }
};

int main(){
    int L,R,cnt=0;
    scanf("%d%d",&L,&R);
    Bit *primes=new Bit[R+1]();
    primes[0]=primes[1]=0;
    for(int i=2;i*i<=R;i++){
        if(primes[i]){
            for(int j=i*i;j<=R;j+=i)
                primes[j]=0;
        }
    }
    for(int i=L;i<=R;i++)
        if(primes[i])
            cnt++;
    printf("%d\n",cnt);
}