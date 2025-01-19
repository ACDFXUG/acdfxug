#include <iostream>

constexpr const char *const tian_gan[]{
    "jia","yi","bing","ding","wu",
    "ji","geng","xin","ren","gui"
};

constexpr const char *const di_zhi[]{
    "zi","chou","yin","mao",
    "chen","si","wu","wei",
    "shen","you","xu","hai"
};

int main(){
    unsigned short year;
    scanf("%hu",&year);
    printf("%s%s\n",tian_gan[(year+6)%10],di_zhi[(year+8)%12]);
}