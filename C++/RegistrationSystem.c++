#include <iostream>
#include <cstring>
#include <set>
typedef std::string String;
#define Set std::set

int main(){
    int n;
    std::cin>>n;
    Set<String> s;
    for(char username[33];n-->0;){
        scanf("%s",username);
        if(!s.contains(username)){
            s.insert(username);
            printf("OK\n");
        }else{
            for(int i=1;;i++){
                String new_username=username+std::to_string(i);
                if(!s.contains(new_username)){
                    s.insert(new_username);
                    printf("%s\n",new_username.c_str());
                    break;
                }else{
                    continue;
                }
            }
        }
    }
}