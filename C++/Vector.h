#include <iostream>
#include <cmath>
#include <vector>
#define vctd std::vector<double>

class Vector{
    private:
    vctd v;
    public:
    Vector():v(vctd()){}
    Vector(int l):v(vctd(l)){}
    Vector(int l,double init):v(vctd(l,init)){}
    Vector(vctd v):v(v){}
    Vector(std::initializer_list<double> ct){
        for(auto i:ct){
            this->v.push_back(i);
        }
    }
    Vector(double *v,int l){
        for(int i=0;i<l;i++){
            this->v.push_back(v[i]);
        }
    }
    ~Vector(){}
    Vector clone(){
        return Vector(v);
    }
    void pushBack(double x){
        v.push_back(x);
    }
    void popBack(){
        v.pop_back();
    }
    int size() const{
        return v.size();
    }
    double at(int index) const{
        return v.at(index);
    }
    double &operator [](const int &index){
        return v[index];
    }
    double operator [](const int &index) const{
        return v[index];
    }
    bool operator ==(const Vector &v) const{
        if(this==&v){
            return true;
        }
        return v==v.v;
    }
    Vector operator +(const Vector &v){
        if(size()!=v.size()){
            throw "Vector size mismatch";
        }
        vctd V(size());
        for(int i=0;i<size();i++){
            V[i]=this->v[i]+v.v[i];
        }
        return Vector(V);
    }
    Vector &operator +=(const Vector &vv){
        if(size()!=vv.size()){
            throw "Vector size mismatch";
        }
        for(int i=0;i<size();i++){
            v[i]+=vv.v[i];
        }
        return *this;
    }
    Vector operator -(const Vector &vv){
        if(size()!=vv.size()){
            throw "Vector size mismatch";
        }
        vctd V(size());
        for(int i=0;i<size();i++){
            V[i]=this->v[i]-vv.v[i];
        }
        return Vector(V);
    }
    Vector &operator -=(const Vector &vv){
        if(size()!=vv.size()){
            throw "Vector size mismatch";
        }
        for(int i=0;i<size();i++){
            v[i]-=vv.v[i];
        }
        return *this;
    }
    Vector operator *(const Vector &vv){
        if(size()!=vv.size()){
            throw "Vector size mismatch";
        }
        vctd V(size());
        for(int i=0;i<size();i++){
            V[i]=this->v[i]*vv.v[i];
        }
        return Vector(V);
    }
    Vector operator *(const double &x){
        vctd V(size());
        for(int i=0;i<size();i++){
            V[i]=this->v[i]*x;
        }
        return Vector(V);
    }
    friend Vector operator *(const double &x,const Vector &v){
        return Vector(v.v)*x;
    }
    Vector &operator *=(const Vector &vv){
        if(size()!=vv.size()){
            throw "Vector size mismatch";
        }
        for(int i=0;i<size();i++){
            v[i]*=vv.v[i];
        }
        return *this;
    }
    Vector &operator *=(const double &x){
        for(int i=0;i<size();i++){
            v[i]*=x;
        }
        return *this;
    }
};
