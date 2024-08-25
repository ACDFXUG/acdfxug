#include <iostream>

std::string dogNames(size_t N){
    std::string name="";
    while(N>0){
        if(N%26==0){
            name='z'+name;
            (--N)/=26;
        }else{
            name=char(N%26+'a'-1)+name;
            N/=26;
        }
    }
    return name;
}

int main(){
    size_t N;
    std::cin>>N;
    printf("%s\n",dogNames(N).data());
}