#include <iostream>
#include <iomanip>
#include <cmath>
#include <stdlib.h>
using namespace std;
int main()
{ 
    int a,b,c;
    int i=0,k=a,l=b;
    cin>>a>>b>>c;
    while(a!=0&&b!=0&&c!=0){
        if(a<=b){  for(int k;k<=b;k++){
            
            if(c%k==0){
           i++;
            }
        } 
        cout<<i;
        
        cin>>a>>b>>c;
        }else if(a>b){
             for(int l;l<=a;l++){
                
                if(c%l==0){
                i++;
             }
            }
            cout<<i;
            cin>>a>>b>>c;
           
        }
        
        
    } 
    
}