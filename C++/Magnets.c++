#include <iostream>
#define String std::string

bool isRepulse(String a,String b){
    bool m1,m2;
    if(a=="10"){
        m1=true;
    }else if(a=="01"){
        m1=false;
    }

    if(b=="10"){
        m2==true;
    }else if(b=="01"){
        m2=false;
    }
    return m1^m2;
}

int main(){
    int n,t=1;
    std::cin>>n;
    String *p=new String[n];
    for(int i=0;i<n;i++){
        std::cin>>p[i];
    }
    for(int i=1;i<n;i++){
        if(isRepulse(p[i-1],p[i])){
            t++;
            printf("%d\n",t);
        }
    }
    std::cout<<t<<std::endl;
    delete[] p;
}