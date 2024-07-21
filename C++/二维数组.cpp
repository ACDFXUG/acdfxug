#include <iostream>
#include <iomanip>
#include <cmath>
#include <stdlib.h>
using namespace std;
int main()
{
        int m,n,a[n][m],b[m];
        cin>>n>>m;
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                cin>>a[j][i];
            }
        }
        for(int i=1;i<=m;i++){
            cin>>b[i];
        }
        for(int j=1;j<=n;j++){
            for(int i=1;i<=m;i++){
                cout<<a[j][i]*b[i]<<endl;
            }
        }
}