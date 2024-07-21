#include <iostream>

bool isCentralSymmetry(char p[3][3]){
    for(int j=0;j<3;j++){
        if(p[0][j]!=p[2][2-j]){
            return false;
        }
    }
    if(p[1][0]!=p[1][2]){
        return false;
    }
    return true;
}

int main(){
    char p[3][3];
    for(int i=0;i<3;i++){
        for(int j=0;j<3;j++){
            std::cin>>p[i][j];
        }
    }
    printf(isCentralSymmetry(p)?"YES\n":"NO\n");
}