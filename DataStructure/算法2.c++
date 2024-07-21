#include <iostream>
#include <queue>
#include <stack>
using namespace std;
void reverse(queue<int> Q,stack<int> S){
while(!Q.empty()){
S.push(Q.front());
Q.pop();
} 
while(!S.empty()){
Q.push(S.top());
S.pop();
}
}
int main(){
    queue<int> Q;
    stack<int> S;
    int m;
    do{
        cin>>m;
        Q.push(m);
    }while (cin.get()!='\n');   
    reverse(Q,S);
    while(!Q.empty()){
    cout<<Q.front()<<" ";
    Q.pop(); 
}       
}