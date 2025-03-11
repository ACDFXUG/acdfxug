#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

class Fraction{
public:
    int u,l;
    Fraction(int a=0,int b=1):u(a),l(b){}
    Fraction(const Fraction &f):u(f.u),l(f.l){}
    Fraction(Fraction &&f):u(f.u),l(f.l){}
    bool operator <(const Fraction &f) const{
        return l*f.l>0?u*f.l<f.u*l:u*f.l>f.u*l;
    }
    Fraction &operator =(const Fraction &f){
        u=f.u;
        l=f.l;
        return *this;
    }
    Fraction &operator =(Fraction &&f){
        u=f.u;
        l=f.l;
        return *this;
    }
};

vector<int> kthSmallestPrimeFraction(vector<int> &arr, int k) {
    const int len=arr.size();
    vector<Fraction> prime_fractions;
    prime_fractions.reserve(len*(len-1)>>1);
    for(int i=0;i<len;++i){
        for(int j=i+1;j<len;++j){
            prime_fractions.emplace_back(arr[i],arr[j]);
        }
    }
    sort(prime_fractions.begin(),prime_fractions.end());
    const auto &[u,l]=prime_fractions[k-1];
    return {u,l};
}

int main(){
    vector<int> arr{1,2,3,5};
    auto ans=std::move(kthSmallestPrimeFraction(arr,3));
    cout<<ans[0]<<"/"<<ans[1]<<endl;
}