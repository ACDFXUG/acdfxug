#include <iostream>
#include <stack>
using namespace std;
int main(){
    stack<char> S;
    S.push('a');
    S.push('b');
    S.pop();
    S.push('c');
    S.push('d');
    S.pop();
    S.pop();
    S.push('e');
    char p;
    do{
        p=S.top();
        S.pop();
        cout<<p;
    }while(!S.empty());
}