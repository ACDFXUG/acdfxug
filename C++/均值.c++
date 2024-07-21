#include <iostream>
int main(){
    int n;
    scanf("%d",&n);
    double sum=0,a;
    for(int i=1;i<=n;i++){
        scanf("%lf",&a);
        sum+=a;
    }
    printf("%f",sum/n);
}