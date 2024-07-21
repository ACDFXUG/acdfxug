#include<iostream>
#include<string>
using namespace std;
int main()
{
    int n;
    cin>>n;
    string s[10],str[1000];
    for(int i=0;i<n;i++){        
        cin>>s[i];        
        int L=s[i].length(); 
        s[i]+=s[i];
        int p=0;       
        for(int j=1;j<=L;j++){
            int t=0;
            for(int k=0;k<L;k++){
                str[t]=s[i].substr(k,j);
                t++;
            }
            for(int k=0;k<L;k++){
                for(int q=k+1;q<L;q++){
                    if(str[k]==str[q]&&str[k]!=""&&str[q]!=""){
                        str[q]="";
                    }
                }
            }
            for(int k=0;k<t;k++){
                if(str[k]!=""){
                    p++;
                }
            }
        }
        cout<<p<<endl;
    }
}