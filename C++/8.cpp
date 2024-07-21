#include <iostream>
#include <iomanip>
#include <cmath>
#include <stdlib.h>
using namespace std;
int main()
{
     int x,y;
    cin>>x>>y;
    while(x!=0&&y!=0){
        if(x>=y){
            cout<<y<<" "<<x<<endl;
            cin>>x>>y;
        }else{
            cout<<x<<" "<<y<<endl;
            cin>>x>>y;
        }
    }
   return 0;    
}