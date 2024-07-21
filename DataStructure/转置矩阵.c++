#include <iostream>
using namespace std;
int matrix[1000][1000];
int mat[1000][1000];
int main(){
    int L,W,N;
    cout<<"input row col number "<<endl;
    cin>>L>>W>>N;
    while(N--){
        int i,j,n;
        cout<<"input row col value"<<endl;
        cin>>i>>j>>n;
        mat[j][i]=matrix[i][j]=n;
    }
    cout<<"matrix:"<<endl;
    for(int i=1;i<=L;i++){
        for(int j=1;j<=W;j++){
            cout<<matrix[i][j]<<" ";
        }
        cout<<endl;
    }
    cout<<"reverse:"<<endl;
    for(int j=1;j<=W;j++){
        for(int i=1;i<=L;i++){
            cout<<mat[j][i]<<" ";
        }
        cout<<endl;
    }
}
