#include <iostream>
#include <cstring>
using namespace std;
int main()
{
    int a;
    cin>>a;
    string s=to_string(a);
    int l=s.length();
    char* p=new char[l];
    strcpy(p,s.c_str());
    for(int i=0;i<l;i++){
        cout<<p[i]<<endl;
    }
    delete[] p;
}