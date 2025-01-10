#include <iostream>
#include <cmath>
#include <cstring>
#include <algorithm>
#include <list>
#include <regex>
#include "BigInteger.h"
#include "BigDecimal.h"
typedef long long ll;
typedef std::string String;

class Fraction{
    private:
    ll up,low;
    static ll GCD(ll a,ll b){
        ll shift=0;
        if(a<b){
            std::swap(a,b);
        }
        if(b==0ll){
            return 0;
        }
        for(;a!=b;){
            if(a&1){
                if((b&1)==1){
                    b=(a-b)>>1;
                    a-=b;
                }else{
                    b>>=1;
                }
            }else{
                if(b&1){
                    a>>=1;
                    if(a<b){
                        std::swap(a,b);
                    }
                }else{
                    a>>=1;
                    b>>=1;
                    shift++;
                }
            }
        }
        return a<<shift;
    }
    static ll gcd(ll a,ll b){
        return GCD(llabs(a),llabs(b));
    }
    static ll power(ll a,ll b){
        ll res=1;
        for(;b>0;b>>=1){
            if(b&1){
                res*=a;
            }
            a*=a;
        }
        return res;
    }
    public:
    Fraction(ll up,ll low){
        if(up<0ll&&low<0ll){
            up=-up;
            low=-low;
        }
        this->up=up;
        this->low=low;
    }
    Fraction(ll integer):
    Fraction(integer,1){}
    Fraction():Fraction(1ll,1ll){}
    ~Fraction(){}
    ll getUp(){
        return up;
    }
    static ll getUp(Fraction f){
        return f.up;
    }
    ll getLow(){
        return low;
    }
    static ll getLow(Fraction f){
        return f.low;
    }
    ll getGcd(){
        return gcd(up,low);
    }
    ll getInt(){
        return up/low;
    }
    ll getInt(Fraction f){
        return f.getInt();
    }
    //String getFloat(){}
    String toString() const{
        if(low==1ll){
            return std::to_string(up);
        }else if(up==0ll){
            return "0";
        }else if(low==0){
            return "Inf";
        }else{
            return std::to_string(up)+"/"+std::to_string(low);
        }
    }
    int intValue(){
        return (int)getInt();
    }
    ll longValue(){
        return getInt();
    }
    float floatValue(){
        return up*1.0F/low;
    }
    double doubleValue(){
        return up*1.0/low;
    }
    Fraction operator |(const ll &x){
        return Fraction(up/x,low/x);
    }
    Fraction &operator |=(const ll &x){
        return *this=*this|x;
    }
    Fraction operator &(const ll &x){
        return Fraction(up*x,low*x);
    }
    Fraction &operator &=(const ll &x){
        return *this=*this&x;
    }
    Fraction operator !(){
        return *this|gcd(up,low);
    }
    Fraction toLowest(){
        return !(*this);
    }
    static Fraction toLowest(Fraction f){
        return !f;
    }
    Fraction clone(){
        return Fraction(up,low);
    }
    Fraction operator [](const int &x){
        switch(x){
            case 0:return up;
            case 1:return low;
        }
    }
    Fraction &operator ()(const ll &x,const ll &y){
        up=x;
        low=y;
        return *this=!(*this);
    }
    Fraction operator +(const Fraction &f){
        return !Fraction(up*f.low+low*f.up,low*f.low);
    }
    Fraction operator +(const ll &x){
        return !(*this+Fraction(x,1ll));
    }
    friend Fraction operator +(const ll &x,const Fraction &f){
        return !Fraction(f.up+x*f.low,f.low);
    }
    Fraction &operator +=(const Fraction &f){
        *this=*this+f;
        return *this=!(*this);
    }
    Fraction &operator +=(const ll &x){
        *this+=Fraction(x,1ll);
        return *this=!(*this);
    }
    Fraction operator -(){
        return Fraction(-up,low);
    }
    Fraction operator -(const Fraction &f){
        return !Fraction(up*f.low-low*f.up,low*f.low);
    }
    Fraction operator -(const ll &x){
        return !(*this-Fraction(x,1ll));
    }
    friend Fraction operator -(const ll &x,const Fraction &f){
        return !Fraction(f.up-x*f.low,f.low);
    }
    Fraction &operator -=(const Fraction &f){
        *this=*this-f;
        return *this=!(*this);
    }
    Fraction &operator -=(const ll &x){
        *this-=Fraction(x,1ll);
        return *this=!(*this);
    }
    Fraction operator *(const Fraction &f){
        return !Fraction(up*f.up,low*f.low);
    }
    Fraction operator *(const ll &x){
        return !Fraction(up*x,low);
    }
    friend Fraction operator *(const ll &x,const Fraction &f){
        return !Fraction(f.up*x,f.low);
    }
    Fraction &operator *=(const Fraction &f){
        *this=*this*f;
        return *this=!(*this);
    }
    Fraction &operator *=(const ll &x){
        *this=*this*x;
        return *this=!(*this);
    }
    Fraction operator /(const Fraction &f){
        return !Fraction(up*f.low,low*f.up);
    }
    Fraction operator /(const ll &x){
        return !Fraction(up,low*x);
    }
    friend Fraction operator /(const ll &x,const Fraction &f){
        return !Fraction(f.low*x,f.up);
    }
    Fraction &operator /=(const Fraction &f){
        *this=*this/f;
        return *this=!(*this);
    }
    Fraction &operator /=(const ll &x){
        *this=*this/x;
        return *this=!(*this);
    }
    bool operator ==(const Fraction &f) const{
        if(this==&f)return true;
        return up==f.up&&low==f.low;
    }
    bool operator !=(const Fraction &f) const{
        return !(*this==f);
    }
    bool operator <(const Fraction &f) const{
        return low*f.low>0?up*f.low<low*f.up:up*f.low>low*f.up;
    }
    bool operator >(const Fraction &f) const{
        return low*f.low>0?up*f.low>low*f.up:up*f.low<low*f.up;
    }
    bool operator <=(const Fraction &f) const{
        return !(*this>f);
    }
    bool operator >=(const Fraction &f) const{
        return !(*this<f);
    }
    bool operator ==(const ll &x) const{
        return up==x*low;
    }
    bool operator !=(const ll &x) const{
        return !(*this==x);
    }
    bool operator <(const ll &x) const{
        return low>0?up<x*low:up>x*low;
    }
    bool operator >(const ll &x) const{
        return low>0?up>x*low:up<x*low;
    }
    bool operator <=(const ll &x) const{
        return !(*this>x);
    }
    bool operator >=(const ll &x) const{
        return !(*this<x);
    }
    Fraction operator ^(const int &n){
        Fraction ans(1,1);
        for(int i=n;i-->0;ans*=*this);
        return ans;
    }
    Fraction operator ^=(const int &n){
        return *this=*this^n;
    }
    int compareTo(const Fraction &f) const{
        if(*this==f){
            return 0;
        }else if(*this<f){
            return -1;
        }else{
            return 1;
        }
    }
    static Fraction parseFraction(double x){
        return parseFraction(std::to_string(x));
    }
    static Fraction parseFraction(String ratio){
        String reg1=R"((\d+)\.(\d+)\((\d+)\))";
        String reg2=R"((\d+)\.\((\d+)\))";
        String reg3=R"((\d+)\.(\d+))";
        if(std::regex_match(ratio,std::regex(reg1))){
            ll a=std::stoll(ratio.substr(0,ratio.find(".")));
            ll b=std::stoll(ratio.substr(ratio.find(".")+1,ratio.find("(")-ratio.find(".")-1));
            ll c=std::stoll(ratio.substr(ratio.find("(")+1,ratio.find(")")-ratio.find("(")-1));
            int m=ratio.substr(ratio.find(".")+1,ratio.find("(")-ratio.find(".")-1).length();
            int n=ratio.substr(ratio.find("(")+1,ratio.find(")")-ratio.find("(")-1).length();

            Fraction inte(a,1ll);
            Fraction dec1(b,power(10,m));
            Fraction dec2(c,power(10,m+n)-power(10,m));
            return inte+dec1+dec2;
        }else if(std::regex_match(ratio,std::regex(reg2))){
            ll a=std::stoll(ratio.substr(0,ratio.find(".")));
            ll b=std::stoll(ratio.substr(ratio.find(".")+1,ratio.find("(")-ratio.find(".")-1));
            int m=ratio.substr(ratio.find(".")+1,ratio.find("(")-ratio.find(".")-1).length();

            Fraction inte(a,1ll);
            Fraction dec(b,power(10,m)-1);
            return inte+dec;
        }else if(std::regex_match(ratio,std::regex(reg3))){
            ll a=std::stoll(ratio.substr(0,ratio.find(".")));
            ll b=std::stoll(ratio.substr(ratio.find(".")+1,ratio.length()-ratio.find(".")-1));
            int m=ratio.substr(ratio.find(".")+1,ratio.length()-ratio.find(".")-1).length();

            Fraction inte(a,1ll);
            Fraction dec(b,power(10,m));

            return inte+dec;
        }else{
            return Fraction::parseFraction(std::to_string(std::stod(ratio)));
        }
    }
    static Fraction valueOf(String f){
        if(f.contains("/")){
            return Fraction(std::stoll(f.substr(0,f.find("/"))),
            std::stoll(f.substr(f.find("/")+1,f.length()-f.find("/")-1)));
        }else{
            throw "Invalid fraction string";
        }
    }
    Fraction operator ~(){
        return Fraction(low,up);
    }
    private:
    std::list<ll> primeFactors(ll n){
        std::list<ll> ans;
        for(;(n&1)==0;n>>=1,ans.push_back(2));
        for(ll i=3;i*i<=n;i+=2){
            for(;n%i==0;n/=i,ans.push_back(i));
        }
        if(n>2){
            ans.push_back(n);
        }
        return ans;
    }
    bool containsNotOnly2And5(std::list<ll> &l){
        for(auto i:l){
            if(i!=2&&i!=5){
                return true;
            }
        }
        return false;
    }
    int loopLength(ll r){
        BigInteger a=BigInteger(10);
        for(int i=1;;i++){
            if((a.pow(i))%BigInteger(r)==BigInteger(1)){
                return i;
            }
        }
    }
    String replaceEnd(String s,String a,String b) const{
        String s2=s;
        for(int i=s.length()-1;i>=0;i--){
            if(s[i]==a[0])s2[i]=b[0];
            else if(s[i]=='.')s2[i]=b[0];
            else break;
        }
        return s2;
    }
    public: 
    String toRational(){
        ll u=toLowest().up,d=toLowest().low;
        std::list<ll> factors=primeFactors(d);
        if(u%d==0){
            return std::to_string(u/d);
        }
        if(!containsNotOnly2And5(factors)){
            return replaceEnd(std::to_string(u*1.0/d),"0","");
        }else{
            int two=0,five=0;
            for(auto i:factors){
                if(i==2){
                    two++;
                }else if(i==5){
                    five++;
                }
            }
            int c=std::max(two,five);
            ll r=d/(power(2,two)*power(5,five));
            int loop=loopLength(r);
            int intelen=std::to_string(getInt()).length();
            BigDecimal a1(u*1.0),a2(d*1.0);
            BigDecimal de=a1/a2;
            de.setAccuracy(loop+c+intelen);
            String ans=de.toString().substr(0,intelen+loop+c+1);
            return ans.insert(intelen+1+c,"(").append(")");
        }
    }
    Fraction abs(){
        return Fraction(std::abs(up),std::abs(low));
    }
    static Fraction abs(const Fraction &_f){
        return Fraction(std::abs(_f.up),std::abs(_f.low));
    }
    friend std::istream& operator >>(std::istream &in,Fraction &_f){
        String f;
        in>>f;
        if(f.find("/")!=std::string::npos){
            std::list<String> l;
            for(int i=0;i<f.length();i++){
                if(f[i]=='/'){
                    l.push_back(f.substr(0,i));
                    l.push_back(f.substr(i+1,f.length()-i-1));
                    break;
                    
                }
            }
            _f.up=std::stoll(l.front());
            _f.low=std::stoll(l.back());
        }else{
            _f.up=std::stoll(f);
            _f.low=1ll;
        }
        return in;
    }
    friend std::ostream& operator <<(std::ostream &out,const Fraction &_f){
        out<<_f.toString();
        return out;
    }
};