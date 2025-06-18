#include <iostream>
#include <format>
#include <vector>

int main(){
    int n,m;
    std::cin>>n>>m;
    std::vector<std::string> question(m);
    for(int i=0;i<m;std::cin>>question[i++]);
    for(int i=0;i<n;++i){
        std::string name,file_tree;
        std::cin>>name;
        for(int j=0;j<m;++j){
            auto real=std::format("{0}.zip/{0}/{1}/{1}.cpp",name,question[j]);
            std::cin>>file_tree;
            std::cout<<(file_tree==real?"Fusu is happy!\n":"Fusu is angry!\n");
        }
    }
}