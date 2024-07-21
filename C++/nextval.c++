#include <bits/stdc++.h>
using namespace std;
void getNextval(char T[],int TLength) {
    int nextval[TLength];
    nextval[1] = 0;
    printf("nextval[1] = %d \n", nextval[1]);
    int i = 1, j = 0; //i表示数组下标，初始化为1；j表示next数组的值，初始化为0
    while (i < TLength-1) {

        if (j == 0 || T[i] == T[j]) {
            i++;
            j++;
            if(T[i] != T[j]) { //不同，该位置的nextval的值就是该位置的next值
                nextval[i] = j;
                printf("nextval[%d] = %d \n", i, nextval[i]);
            } else{ //相同，该位置的nextval的值就是该位置next值对应元素的nextval的值
                nextval[i] = nextval[j];
                printf("nextval[%d] = %d \n", i, nextval[i]);
            }
        }else{
            j = nextval[j] ;
        }
    }
}
int main(){
    string S;
    cin>>S;
    char s[S.length()];
    strcpy(s,S.c_str());
    int l=S.length();
    getNextval(s,l);
}