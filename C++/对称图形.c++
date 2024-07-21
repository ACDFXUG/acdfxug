#include<iostream>
#include<string>
#include<algorithm>
#include<cstring>
using namespace std;
int main()
{   
    int x=1,y=1,z=1,feel=0,n;
    cin>>n;   
    cin.get();
    string str[100];
    for(int i=0;i<n;i++){
        getline(cin,str[i]);
        int l=str[i].size();       
        while(l>0){
            l--;
            int p=str[i].find(' ');
            if(p!=string::npos)
                str[i].erase(p,1);
        }
        if(str[i].empty()){
            i--;
            n--;
        }
    }
    for(int i=0,j=n-1;j>i;i++,j--){
        if(str[i]!=str[j]){
            x=0;
            break;
        }
    }
    for(int i=0;i<n;i++){
        for(int j=0,k=str[i].size()-1;k>j;j++,k--){
            if(str[i].at(j)!=str[i].at(k))
                y=0;
        }
    }
    if(n!=str[0].size()){
        z=0;
    }else{
        for(int i=0,j=n-1;i<=j;i++,j--){
            string s=str[i];
            reverse(s.begin(),s.end());
            if(s!=str[j]){
                z=0;
                break;
            }
        }
    }   
    if(x){
        feel+=2;
    }
    if(y){
        feel+=1;
    }
    if(z){
        feel+=3;
    }
    cout<<feel<<endl;
}