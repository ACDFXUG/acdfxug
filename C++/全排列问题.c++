#include <iostream>
#include <algorithm>
#include <cassert>
#include <iomanip>

int fraction(const int &n){
    assert(1<=n&&n<=9);
    switch(n){
        case 1: return 1;
        case 2: return 2;
        case 3: return 6;
        case 4: return 24;
        case 5: return 120;
        case 6: return 720;
        case 7: return 5040;
        case 8: return 40320;
        case 9: return 362880;
        default: return 0;
    }
}

int main(){
    int n;
    scanf("%d",&n);
    int *per=new int[n]();
    for(int i=0;i<n;i++) per[i]=i+1;
    for(int i=0;i<fraction(n);i++){
        for(int j=0;j<n;j++){
            std::cout<<std::setw(5)<<per[j];
        }
        std::next_permutation(per,per+n);
        printf("\n");
    }
    delete[] per;
}