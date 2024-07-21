#include <iostream>

int main(){
    double sum=0,a;
    do{
        scanf("%lf",&a);
        sum+=a;
    }while(getchar()!='\n');
    printf("%.5f\n",sum);
}