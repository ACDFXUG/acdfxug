#include <iostream>
using namespace std;
int main(){
    double guo=100,wang=100;
    for(int i=0;i<3650;i++){
        guo=1.001*guo;
    }
    for(int i=0;i<3650/5;i++){
        wang=1.002*1.002*1.002*0.999*0.999*wang;
    }
    printf("%.2f\n",guo);
    printf("%.2f\n",wang);
}