#include <iostream>
using namespace std;
int main()
{
    int a,b;
    char op;
    cin>>a>>op>>b;
   while(op!='?'&&a!=0&&b!=0){
    switch (op)
    {
    case '+':
    cout<<a+b<<endl;
        break;
     case '-':
       cout<<a-b<<endl;
       break; 
    case '*':
    cout <<a*b<<endl;
    break;
    case '/':
    cout<<a/b<<endl;
    break;
    
        
    }
    cin>>a>>op>>b;
    }
}