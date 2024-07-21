#include <iostream>
using namespace std;
int main(){
    int n;
    cin>>n;
    for(int i=1;i<=n;i++){
        string W,T;
        cin>>W>>T;
        int a=0,b=0;
        for(;(a=T.find(W,a))!=T.npos;){
            a++;  //开始查找的下标和找到子串的首字母下标
            b++;  //字串数目，相当于计数器
        }
        cout<<b<<endl;
    }
}