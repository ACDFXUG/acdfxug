#include <iostream>
#include <algorithm>
#define max(x,y) (x>y?x:y)
#define min(x,y) (x<y?x:y)

int main(){
    int n;
    std::cin>>n;
    double sum,a1,a2,a;
    scanf("%lf%lf",&a1,&a2);
    double max=max(a1,a2);
    double min=min(a1,a2);
    sum=a1+a2;
    for(int i=1;i<=n-2;i++){
        scanf("%lf",&a);
        max=max(max,a);
        min=min(min,a);
        sum+=a;
        printf("%.2f\n",(sum-max-min)/i);
    }
}