#include<iostream>
using namespace std;
int main()
{
    int H,W;
    while(cin>>H>>W){
        if(H!=0&&W!=0){
            for(int i=1;i<=H;i++){
                for(int j=1;j<=W;j++){
                    cout<<"#";
                }
                cout<<endl;
            }
            cout<<endl;
        }else{
            break;
        }       
    }
}