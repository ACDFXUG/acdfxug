#include<iostream>
using namespace std;
int main()
{
    long long N,j,sum=0;
    cin>>N;
    for(int i=2;i<N;i++){
        for(j=2;j<i;j++){
            if(i%j==0){
                break;
            }
        }
        if(j==i){
            sum+=i;
        }
    }       
        cout<<sum<<endl;   
}