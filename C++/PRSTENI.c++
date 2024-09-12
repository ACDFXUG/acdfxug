#include <iostream>

int GCD(int a,int b){
    return b==0?a:GCD(b,a%b);
}

int main(){
    int n;
    std::cin>>n;
    int *rad=new int[n];
    for(int i=0;i<n;scanf("%d",rad+i++));
    for(int i=1;i<n;i++){
        int gcd=GCD(rad[i],rad[0]);
        printf("%d/%d\n",rad[0]/gcd,rad[i]/gcd);
    }
    delete[] rad;
}