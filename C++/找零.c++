#include <iostream>
using namespace std;

int s[7];
int facevalue[7]={1,2,5,10,20,50,100};
int change(int p, int s[7])
{
    int l=0,num=0;
    for(int i=0;i<7;i++){
        l+=s[i]*facevalue[i];
    }
    if(p>l){
            return -1;
    }
    for(int i=6;i>=0;i--){
        while (p>=facevalue[i]&&s[i]>0){   
        p-=facevalue[i];
        s[i]--;
        num++;
    }
    }
    if(p>0){
        return -1;
    }else{
        return num;
    }    
}

int main()
{
    int n,p;
    cin>>n;
    for(int i=0;i<n;i++){    
        cin>>p;
        for (int j=0; j<7;j++){      
            cin>>s[j];
        }
            cout <<change(p, s)<<endl;
    }
}