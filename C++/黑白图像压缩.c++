#include <iostream>
#include <cstring>
using namespace std;
int POWER(int a, int x){
    int pow=1;
    for(int i=1;i<=x;i++){
        pow*=a;
    }
    return pow;
}

static string toBINSTR(int n){
    string s="";
    for(;n>0;n>>=1){
        s=to_string(n%2)+s;
    }
    return s;
}
static string eight_bit(int n){
    string s=toBINSTR(n);
    int l=s.length();
    for(int i=0;i<8-l;i++){
        s="0"+s;
    }
    return s;
}

static bool contains(string s,char l){
    for(int i=0;i<s.length();i++){
        if(s[i]==l){
            return true;
        }
    }
    return false;
}

static string ebp(string s){
    string BIN=toBINSTR(s.length());
    int L=BIN.length();
    for(int i=1;i<8-L;i++){
        BIN="0"+BIN;
    }
    return contains(s,'0')?"0"+BIN:"1"+BIN;
}

string toDECSTR(string bin){
    int dec=0;
    for(int i=bin.length()-1;i>=0;i--){
        if(bin[i]=='1'){
            dec+=POWER(2,bin.length()-1-i);
        }
    }
    return to_string(dec);
}

static string l_s(string s,int i){
    string p="";
    for(int q=0;q<i;q++){
        p+=s[q];
    }
    return p;
}

static string r_s(string s,int i){
    string p="";
    for(int q=0;q<s.length()-i;q++){
        p+=s[i+q];
    }
    return p;
}

int main(){
    int n;
    scanf("%d",&n);
    string pixel="";
    for(int i=0;i<n/8;i++){
        int v;
        scanf("%d",&v);
        pixel+=eight_bit(v);
    }
    int t=0,L=pixel.length();
    pixel+="#";
    string *PIXEL=new string[L];
    for(int i=0;i<pixel.length();i++){
        if(pixel[i]!=pixel[0]){
            PIXEL[t++]=l_s(pixel,i);
            pixel=r_s(pixel,i);
            i=0;
        }
    }
    for(int i=0;i<t;i++){
        printf("%s ",toDECSTR(ebp(PIXEL[i])).c_str());
    }
    delete[] PIXEL;
}