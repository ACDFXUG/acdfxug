#include <iostream>
#include <iomanip>
#include <cmath>
#include <stdlib.h>
using namespace std;
int main()
{
    int T,x,i=1;
    cin>>T;
for(i;i<=T;i++){ cin>>x;
    if(x>=0&&x<=59){
        cout<<"E"<<endl;
    }else if(x>=60&&x<=69){
        cout<<"D"<<endl;
    }else if(x>=70&&x<=79){
        cout<<"C"<<endl;
    }else if(x>=80&&x<=89){
        cout<<"B"<<endl;
    }else if(x>=90&&x<=100){
        cout<<"A"<<endl;
    }else{
        cout<<"Score is error!";
    }
}
return 0;
}