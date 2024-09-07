#include <iostream>
#include <string>
#include <cmath>
#include <vector>
#include <regex>
typedef std::string String;

#define ONE Complex(1,0)
#define ZERO Complex()
#define I Complex(0,1)

class Complex{
    private:
    long double _real,_imag;
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
    Complex(long double real,long double imaginary):_real(real),_imag(imaginary){}
    Complex():Complex(0,0){}
    Complex(String z):Complex(valueOf(z)[0],valueOf(z)[1]){}
    Complex(const char *z):Complex(String(z)){}
    ~Complex(){}
    String toString() const{
        String r,i;
        if(std::abs(_real)<1e-10){
            r="0";
        }else{
            r=_real==std::round(_real)?std::to_string((int)_real):replaceEnd(std::to_string(_real),"0","");
        }
        if(std::abs(_imag)<1e-10){
            i="0";
        }else{
            i=_imag==std::round(_imag)?std::to_string((int)_imag):replaceEnd(std::to_string(_imag),"0","");
        }
        if(r=="0"&&i!="0"){
            if(i=="1")return "i";
            if(i=="-1")return "-i";
            return i+"i";
        }else if(i=="0"){
            return r;
        }else{
            if(i=="1")return r+"+i";
            if(i=="-1")return r+"-i";
            return i[0]=='-'?r+"-"+i.substr(1)+"i":r+"+"+i+"i";
        }
    }
    void operator ()(double r,double i){
        _real=r;
        _imag=i;
    }
    Complex clone(){
        return Complex(_real,_imag);
    }
    double magnitude(){
        return hypot(_real,_imag);
    }
    static double magnitude(Complex c){
        return c.magnitude();
    }
    double arg(){
        return atan2(_imag,_real);
    }
    // Complex conjugate(){
    //     return Complex(real,-imag);
    // }

    Complex reciprocal(){
        return 1/(*this);
    }
    Complex normalize(){
        return *this/magnitude();
    }
    Complex nagate(){
        return Complex(-_real,-_imag);
    }
    // double *toExp(){
    //     double ans[]{magnitude(),arg()};
    //     return ans;
    // }

    std::vector<double> toExp(){
        return {magnitude(),arg()};
    }
    long double real(){
        return _real;
    }
    static long double Re(Complex c){
        return c[0];
    }
    long double imag(){
        return _imag;
    }
    static long double Im(Complex c){
        return c[1];
    }
    bool operator ==(const Complex &c) const{
        if(this==&c){
            return true;
        }
        return _real==c[0]&&_imag==c[1];
    }
    Complex& operator =(const Complex &c) {
        _real = c[0];
        _imag = c[1];
        return *this; 
    }
    Complex operator ~(){
        return Complex(_real,-_imag);
    }
    Complex operator +(const long double &c){
        return Complex(_real+c,_imag);
    }
    Complex operator +(const Complex &c){
        return Complex(_real+c[0],_imag+c[1]);
    }
    friend Complex operator +(const long double &c,const Complex &c1){
        return Complex(c+c1[0],c1[1]);
    }
    Complex &operator +=(const long double &c){
        _real+=c;
        return *this;
    }
    Complex &operator +=(const Complex &c){
        _real+=c[0];
        _imag+=c[1];
        return *this;
    }
    Complex operator -(){
        return Complex(-_real,-_imag);
    }
    Complex operator -(const long double &c){
        return Complex(_real-c,_imag);
    }
    Complex operator -(const Complex &c){
        return Complex(_real-c[0],_imag-c[1]);
    }
    friend Complex operator -(const long double &c,const Complex &c1){
        return Complex(c-c1[0],-c1[1]);
    }
    Complex &operator -=(const long double &c){
        _real-=c;
        return *this;
    }
    Complex &operator -=(const Complex &c){
        _real-=c[0];
        _imag-=c[1];
        return *this;
    }
    Complex operator *(const long double &c){
        return Complex(_real*c,_imag*c);
    }
    Complex operator *(const Complex &c){
        return Complex(_real*c[0]-_imag*c[1],_real*c[1]+_imag*c[0]);
    }
    friend Complex operator *(const long double &c,const Complex &z){
        return Complex(c*z[0],c*z[1]);
    }
    Complex &operator *=(const long double &c){
        _real*=c;
        _imag*=c;
        return *this;
    }
    Complex &operator *=(const Complex &c){
        double a=_real,b=_imag;
        _real=a*c[0]-b*c[1];
        _imag=a*c[1]+b*c[0];
        return *this;
    }
    Complex operator /(const long double &c){
        if(c==0){
            throw "Division by zero";
        }
        return Complex(_real/c,_imag/c);
    }
    Complex operator /(const Complex &c){
        if(c==ZERO){
            throw "Division by zero";
        }
        double denominator=c[0]*c[0]+c[1]*c[1];
        return Complex(_real*c[0]+_imag*c[1],_imag*c[0]-_real*c[1])/denominator;
    }
    friend Complex operator /(const long double &c,const Complex &z){
        if(z==ZERO){
            throw "Division by zero";
        }
        return c*Complex(z[0],-z[1])/(z[0]*z[0]+z[1]*z[1]);
    }
    Complex &operator /=(const long double &c){
        if(c==0){
            throw "Division by zero";
        }
        _real/=c;
        _imag/=c;
        return *this;
    }
    Complex &operator /=(const Complex &c){
        if(c==ZERO){
            throw "Division by zero";
        }
        double denominator=c[0]*c[0]+c[1]*c[1];
        double a=_real,b=_imag;
        _real=(a*c[0]+b*c[1]);
        _imag=(b*c[0]-a*c[1]);
        return *this/=denominator;
    }
    Complex operator ^(const int &n){
        Complex ans=ONE;
        for(int i=n;i-->0;ans*=*this){}
        return ans;
    }
    Complex operator ^=(const int &n){
        Complex ans=ONE;
        for(int i=n;i-->0;ans*=*this);
        return *this=ans;
    }
    Complex operator ^(const long double &a){
        return Complex(cos(a*arg()),sin(a*arg()))*pow(magnitude(),a);
    }
    Complex operator ^=(const long double &a){
        return *this=Complex(cos(a*arg()),sin(a*arg()))*pow(magnitude(),a);
    }
    long double operator [](const int &i) const{
        switch(i){
            case 0:return _real;
            case 1:return _imag;
            default:throw "Index out of range";
        }
    }
    long double &operator [](const int &i){
        switch(i){
            case 0:return _real;
            case 1:return _imag;
            default:throw "Index out of range";
        }
    }
    std::vector<Complex> nthrt(int N){
        const double PI=acos(-1);
        if(N<=0){
            throw "N must be greater than 0";
        }
        double r=magnitude(),theta=arg();
        double omegal=pow(r,1.0/N);
        std::vector<Complex> ans;
        for(int i=0;i<N;i++){
            double omega=theta+2*PI*i/N;
            ans.push_back(polar(omega)*omegal);
        }
        return ans;
    }
    std::vector<Complex> operator |(const int &N){
        return nthrt(N);
    }
    static Complex valueOf(String z){
        String onlyReal="[-+]?[0-9]*\\.?[0-9]+";
        String onlyImaginary="[-+]?[0-9]*\\.?[0-9]+i";
        String realAndImaginary="([-+]?[0-9]*\\.?[0-9]+)?([+-][0-9]*\\.?[0-9]+i)";
        if(std::regex_match(z,std::regex(onlyReal))){
            return Complex(std::stod(z),0);
        }else if(std::regex_match(z,std::regex(onlyImaginary))){
            return Complex(0,std::stod(z.substr(0,z.length()-1)));
        }else if(std::regex_match(z,std::regex(realAndImaginary))){
            if(z.contains("+")){
                String r=z.substr(0,z.find("+"));
                return Complex(std::stod(r),std::stod(z.substr(r.length()+1,z.length()-r.length()-1)));
            }else{
                String r=z.substr(0,z.find("-"));
                return Complex(std::stod(r),-std::stod(z.substr(r.length()+1,z.length()-r.length()-1)));
            }
        }else{
            throw "Invalid Complex number";
        }
    }
    friend std::istream &operator >>(std::istream& is, Complex& c) {
        std::string input;
        is >> input; // 先读取一整个字符串
        std::istringstream iss(input);
        
        // 尝试解析字符串
        if (iss >> c._real) { // 先读实部
            char sign;
            if (iss.get(sign) && (sign == '+' || sign == '-')) { // 读取符号
                iss >> c._imag; // 再读虚部
                if (sign == '-') c._imag = -c._imag; // 如果是负号，调整虚部符号
            } else {
                is.setstate(std::ios_base::failbit); // 如果格式不对，设置失败状态
            }
        } else {
            is.setstate(std::ios_base::failbit); // 解析实部失败也设置失败状态
        }
        
        return is;
    }
    friend std::ostream &operator <<(std::ostream& os, const Complex& c) {
        return os << c.toString();
    }
    static Complex polar(long double r,double theta){
        return Complex(cos(theta),sin(theta))*r;
    }
    static Complex polar(double theta){
        return Complex(cos(theta),sin(theta));
    }
};

Complex operator ""_i(long double im){
    return Complex(0,im);
}
Complex operator ""_I(long double im){
    return Complex(0,im);
}
Complex operator ""_i(unsigned long long im){
    return Complex(0,im);
}
Complex operator ""_I(unsigned long long im){
    return Complex(0,im);
}

Complex ln(Complex z){
    if(z==ZERO){
        throw "Ln of zero";
    }
    return Complex(log(z.magnitude()),z.arg());
}
Complex sqrt(Complex z){
    return (z|2)[0];
}
Complex exp(Complex z){
    return Complex(cos(z[1]),sin(z[1]))*exp(z[0]);
}

Complex sin(Complex z){
    return (exp(z)-exp(-z))/2_i;
}

Complex cos(Complex z){
    return (exp(z)+exp(-z))/2;
}

Complex tan(Complex z){
    return sin(z)/cos(z);
}

Complex cot(Complex z){
    return cos(z)/sin(z);
}

Complex sec(Complex z){
    return 1/cos(z);
}

Complex csc(Complex z){
    return 1/sin(z);
}

Complex asin(Complex z){
    return ln(1_i*z+sqrt(1-z*z));
}

Complex acos(Complex z){
    return ln(z+sqrt(1-z*z));
}

Complex atan(Complex z){
    return (ln(1_i+z)-ln(1_i-z))/2_i;
}

Complex acot(Complex z){
    return (ln(1_i+z)-ln(1_i-z))/2;
}

Complex sinh(Complex z){
    return (exp(z)-exp(-z))/2;
}

Complex cosh(Complex z){
    return (exp(z)+exp(-z))/2;
}

Complex tanh(Complex z){
    return sinh(z)/cosh(z);
}

Complex coth(Complex z){
    return cosh(z)/sinh(z);
}
