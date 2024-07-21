#include <iostream>
#include <cstring>
using namespace std;
int main()
{
    string s;
    cin>>s;
    int l=s.length();
    char *p=new char[l];
    strcpy(p,s.c_str());
    int n=0;
    for(int i=0;i<l-2;i++){
        if(p[i]=='t'&&p[i+1]=='j'&&p[i+2]=='u'){
           n++;
        }
    }
    cout<<n<<endl;;
}