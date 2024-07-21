#include <iostream>

int main(){
    int n;
    std::cin>>n;
    double FULL_SCORE=0,REAL_SCORE=0;
    for(int i=0;i<n;i++){
        int a;scanf("%d",&a);
        FULL_SCORE+=a;
    }
    for(int i=0;i<n;i++){
        int a;scanf("%d",&a);
        REAL_SCORE+=a;
    }
    printf("%.6f\n",(FULL_SCORE*3-REAL_SCORE*2)/(FULL_SCORE-REAL_SCORE));
}