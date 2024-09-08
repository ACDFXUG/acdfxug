#include <iostream>

bool isPalindrome(int &n){
    std::string num=std::to_string(n);
    for(int i=0,l=num.length();i<l/2;i++){
        if(num[i]!=num[l-i-1]){
            return false;
        }
    }
    return true;
}

int main(){
    int n,cnt=0;
    scanf("%d",&n);
    for(int i=1;i<=n;i++){
        if(isPalindrome(i)){
            cnt++;
        }
    }
    printf("%d\n",cnt);
}
