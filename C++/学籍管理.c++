#include <iostream>
#include <unordered_map>
typedef std::string String;
#define Map std::unordered_map

int main(){
    int n;
    std::cin>>n;
    Map<String,int> status;
    for(int i=0,act;i<n;i++){
        scanf("%d",&act);
        switch(act){
            case 1:{
                String name;
                int score;
                std::cin>>name>>score;
                status[name]=score;
                printf("OK\n");
                break;
            }case 2:{
                String name;
                std::cin>>name;
                if(status.contains(name)){
                    printf("%d\n",status[name]);
                }else{
                    printf("Not found\n");
                }
                break;
            }case 3:{
                String name;
                std::cin>>name;
                if(status.contains(name)){
                    status.erase(name);
                    printf("Deleted successfully\n");
                }else{
                    printf("Not found\n");
                }
                break;
            }case 4:{
                printf("%d\n",status.size());
                break;
            }
        }
    }
}