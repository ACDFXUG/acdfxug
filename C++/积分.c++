#include <iostream>
#include <cmath>
using namespace std;

int main(){
    double sum=0;
    double A,B;
    cin>>A>>B;
    for(int i=0;i<1000000;i++){
        sum+=sin((A+(B-A)/1000000)*i)*(B-A)/1000000;
    }
    cout<<sum<<endl;
}