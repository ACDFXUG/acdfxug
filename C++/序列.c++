#include <iostream>
using namespace std;
static int ABS_MIN(int A[],int i){
    return i==0?A[0]:min(ABS_MIN(A,i-1),abs(A[i]-A[i-1]));
}

static int SUM_B(int A[],int n){
    int b[100000],sum=0;
    for(int i=0;i<n;i++){
        sum+=i==0?A[i]:ABS_MIN(A,i);
    }
    return sum;
}

int main(){
    int T;
    scanf("%d",&T);
    for(int q=1;q<=T;q++){
        int n;
        scanf("%d",&n);
        int *A=new int[n];
        for(int i=0;i<n;i++){
            scanf("%d",&A[i]);
        }
        printf("%d\n",SUM_B(A,n));
        delete[] A;
    }
}