#include <iostream>
#include <cstring>
#include <cmath>
#include <algorithm>
using std::string;

class String{
    private:
    string str;
    int length;
    char *ch;
    public:
    String(){
        this->str="";
        this->ch=new char[0];
        this->length=0;
    }
    String(string s){
        this->str=s;
        this->length=s.length();
        this->ch=s.data();
    }
    String(char *s){
        this->str=s;
        this->length=this->str.length();
        this->ch=str.data();
    }
    String(const char *s){
        this->str=s;
        this->length=this->str.length();
        this->ch=str.data();
    }
    String(int n,char c){
        this->str=string(n,c);
        this->length=n;
        this->ch=new char[n]{c};
    }
    String(char *s,int n){
        this->str=string(s,n);
        this->length=n;
        this->ch=str.data();
    }
    String(const char *s,int n){
        this->str=string(s,n);
        this->length=n;
        this->ch=str.data();
    }
    char charAt(int i){
        return ch[i];
    }
    char operator [](const int &i){
        return ch[i];
    }
    string tostring(){
        return str;
    }
    char *toCharArray(String s){
        return ch;
    }
    bool equals(String y){
        if(this==&y){
            return true;
        }
        return str==y.str;
    }
    bool operator ==(String s){
        return str==s.str;
    }
    bool contains(char x){
        for(char p:str){
            if(p==x){
                return true;
            }
        }
        return false;
    }
    int indexOf(char x){
        int i=str.find(x);
        return i==str.npos?-1:i;
    }
    int indexOf(String s){
        int i=str.find(s.str);
        return i==str.npos?-1:i;
    }
    String repeat(int l){
        string s="";
        for(int i=0;i<l;i++){
            s+=str;
        }
        return String(s);
    }
    String operator +(const String &s){
        return String(str+s.str);
    }
    String operator +(const char &c){
        return String(str+c);
    }
    String operator +(const char *s){
        return String(str+s);
    }
    String operator +(const string &s){
        return String(str+s);
    }
    String operator +(const long &x){
        return String(str+std::to_string(x));
    }
    // void operator +=(const String &s){
    //     str+=s.str;
    //     length+=s.length;
    //     ch=str.data();
    // }

    String operator +=(const String &s){
        str+=s.str;
        length+=s.length;
        ch=str.data();
        return *this;
    }
    // void operator +=(const char &c){
    //     str+=c;
    //     length++;
    //     ch=str.data();
    // }

    String operator +=(const char &c){
        str+=c;
        length++;
        ch=str.data();
        return *this;
    }
    String subString(int begin,int end){
        return String(str.substr(begin,end));
    }
    String subString(int begin){
        return String(str.substr(begin));
    }
    String replace(char before,char after){
        char *p=new char[length];
        for(int i=0;i<length;i++){
            p[i]=ch[i];
        }
        for(int i=0;i<length;i++){
            if(p[i]==before){
                p[i]=after;
                break;
            }
        }
        return String(p,length);
    }
    String replace(int idx,char p){
        char *s=new char[length];
        for(int i=0;i<length;i++){
            s[i]=ch[i];
        }
        s[idx]=p;
        return String(s,length);
    }
    String replaceAll(char before,char after){
        char *p=new char[length];
        for(int i=0;i<length;i++){
            p[i]=ch[i];
            if(p[i]==before){
                p[i]=after;
            }
        }
        return String(p,length);
    }
    String toUpperCase(){
        char *p=new char[length];
        for(int i=0;i<length;i++){
            if(p[i]>='a'&&p[i]<='z'){
                p[i]=(char)(p[i]-'a'+'A');
            }
        }
        return String(p,length);
    }
    String toLowerCase(){
        char *p=new char[length];
        for(int i=0;i<length;i++){
            if(p[i]>='A'&&p[i]<='Z'){
                p[i]=(char)(p[i]-'A'+'a');
            }
        }
        return String(p,length);
    }
    bool isEmpty(){
        return str=="";
    }
    bool equalsIgnoreCase(String s){
        return toUpperCase().str==s.toUpperCase().str;
    }
    void printS(){
        printf(str.c_str());
    }
    void printLn(){
        printf(str.append("\n").c_str());
    }
    static void printLn(String s){
        s.printLn();
    }
    static void printS(String s){
        s.printS();
    }
    int len(){
        return length;
    }
    static int len(String s){
        return s.len();
    }
    String reverse(){
        char *t=new char[length];
        for(int i=0;i<length;i++){
            t[i]=ch[length-i-1];
        }
        return String(t,length);
    }
    String operator ~(){
        return reverse();
    }
    String insert(int idx,char x){
        char *p=new char[length+1];
        for(int i=0;i<=length;i++){
            if(i!=idx){
                p[i]=ch[i-(i>idx)];
            }else{
                p[i]=x;
            }
        }
        return String(p,length+1);
    }
    String insert(int idx,String x){
        int L=length+x.length;
        char *p=new char[L];
        for(int i=0;i<idx;i++){
            p[i]=ch[i];
        }
        for(int i=0;i<x.length;i++){
            p[i+idx]=x.ch[i];
        }
        for(int i=idx+x.length;i<L;i++){
            p[i]=ch[i-x.length];
        }
        return String(p,L);
    }
    String append(char c){
        return str+c; 
    }
    String append(String x){
        return str+x.str;
    }
    String append(long x){
        return str+std::to_string(x);
    }
    bool operator <(const String &s){
        return str<s.str;
    }
    bool operator >(const String &s){
        return str>s.str;
    }
    bool operator<=(const String &s){
        return str<=s.str;
    }
    bool operator>=(const String &s){
        return str>=s.str;
    }
    static String toString(int x){
        return String(std::to_string(x));
    }
    static String toString(float x){
        return String(std::to_string(x));
    }
    static String toString(double x){
        return String(std::to_string(x));
    }
    static String toString(long x){
        return String(std::to_string(x));
    }
    static String toString(long long x){
        return String(std::to_string(x));
    }
    static String toString(unsigned long long x){
        return String(std::to_string(x));
    }
    int parseInt(){
        return std::atoi(str.c_str());
    }
    long parseLong(){
        return std::atol(str.c_str());
    }
    long long parseLL(){
        return std::atoll(str.c_str());
    }
    double parseDouble(){
        return std::atof(str.c_str());
    }
    private:
    static unsigned long long power(int a,int x){
        unsigned long long ans=1;
        for (int i=0;i<x;i++) {
            ans*=a;
        }
        return ans;
    }
    public:
    /**
     * @brief 将十进制数x转换为radix进制
     * @param x 
     * @param radix 
     * @return 转换后的字符串
     */
    static String toString(unsigned long long x,int radix){
        String ans="",rdx="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if(x==0){
            return"0";
        }
        for(int i=x;i>0;i/=radix){
            ans+=rdx[i%radix];
        }
        return ans.reverse();
    }
    /**
     * @brief 将radix进制字符串转换为10进制
     * @param x 
     * @param radix 
     * @return 转换后的字符串
     */
    static String parseString(String x,int radix){
        unsigned long long ans=0;
        String rdx="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int l=x.length;
        for(int i=0;i<l;i++){
            ans+=rdx.indexOf(x[i])*power(radix,l-i-1);
        }
        return toString(ans);
    }
    String remove(int index){
        char *p=new char[length-1];
        for(int i=0;i<length;i++){
            p[i-(i>index)]=ch[i];
        }
        return String(p,length-1);
    }
    String chop(int begin){
        char *p=new char[begin];
        for(int i=0;i<begin;i++){
            p[i]=ch[i];
        }
        return String(p,begin);
    }
    String chop(int begin,int end){
        char *p=new char[length-1-end+begin];
        for(int i=0;i<begin;i++){
            p[i]=ch[i];
        }
        for(int i=end+1;i<length;i++){
            p[i-end-1+begin]=ch[i];
        }
        return String(p,length-1-end+begin);
    }
    const char *c_str(){
        return str.c_str();
    }
    int compareTo(String y){
        if(str>y.str){
            return 1;
        }else if(str<y.str){
            return -1;
        }else if(str==y.str){
            return 0;
        }else{
            throw;
        }
    }
    static String copyOf(String x,int begin,int end){
        char *p=new char[end-begin+1];
        for(int i=begin;i<=end;i++){
            p[i-begin]=x[i];
        }
        return String(p,end-begin+1);
    }
    
    friend std::ostream& operator <<(std::ostream& os,String s){
        return os<<s.str;
    }

    friend std::istream& operator >>(std::istream& is,String &s){
        return is>>s.str;
    }
};
