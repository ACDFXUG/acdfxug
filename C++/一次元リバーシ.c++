#include <iostream>

int main(){
    std::string wb;
    std::cin>>wb;
    int ans=0;
    for(int i=1,l=wb.length();i<l;i++){
        if(wb[i]!=wb[i-1]){
            ans++;
        }
    }
    std::cout<<ans<<std::endl;
}