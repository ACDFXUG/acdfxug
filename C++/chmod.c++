#include <iostream>
#include <format>

const char chmod[]{'r','w','x'};

int main(){
    int n;
    scanf("%d",&n);
    for(int i=0;i<n;i++){
        std::string mode;
        std::cin>>mode;
        for(int s=0;s<3;s++){
            auto mod=std::format("{:03b}",mode[s]-'0');
            for(int h=0;h<3;h++){
                putchar(mod[h]=='1'?chmod[h]:'-');
            }
        }
        putchar('\n');
    }
}