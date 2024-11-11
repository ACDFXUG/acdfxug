#include <iostream>
#include <stack>

int main(){
    std::stack<std::string> books;
    int Q;
    scanf("%d",&Q);
    for(int i=0;i<Q;i++){
        std::string book;
        std::cin>>book;
        if(book=="READ"){
            printf("%s\n",books.top().c_str());
            books.pop();
        }else{
            books.push(book);
        }
    }
}