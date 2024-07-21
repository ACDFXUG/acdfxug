#include <iostream>

int findMax(int *a,int n){
    int max=0x80000000;
    for(int i=0;i<n;i++){
        max=a[i]>max?a[i]:max;
    }
    return max;
}

int main(){
    int n,k,*ch=new int[26]();
    std::string s;
    std::cin>>n>>k>>s;
    for(char x:s){
        ch[x-'a']++;
    }
    printf(findMax(ch,26)<=k?"YES\n":"NO\n");
    delete[] ch;
}