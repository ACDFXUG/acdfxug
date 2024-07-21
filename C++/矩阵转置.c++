#include <iostream>
using namespace std;
int matrix[1000][1000];
int mat[1000][1000];
int main(){
    int L,W,N;
    cin>>L>>W>>N;
    for(int i,j,n;N>0&&cin>>i>>j>>n;N--){
        mat[j][i]=matrix[i][j]=n;
    }
    for(int i=1;i<=L;i++){
        for(int j=1;j<=W;j++){
            cout<<matrix[i][j]<<" ";
        }
        cout<<endl;
    }
    for(int j=1;j<=W;j++){
        for(int i=1;i<=L;i++){
            cout<<mat[j][i]<<" ";
        }
        cout<<endl;
    }
}

