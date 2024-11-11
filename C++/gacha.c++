#include <iostream>
#include <unordered_set>

int main(){
    int n;
    scanf("%d",&n);
    std::unordered_set<std::string> s;
    for(int i=0;i<n;i++){
        std::string str;
        std::cin>>str;
        s.insert(str);
    }
    printf("%d\n",s.size());
}