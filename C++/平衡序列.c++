#include <iostream>
#include <vector>

class PrefixSum{
private:
    std::vector<int> prefix;
    int current;
public:
    PrefixSum(int n=0):prefix(n+1,0),current(0){}
    void push(const int &x){
        prefix[current+1]=prefix[current]+x;
        ++current;
    }
    const int &operator[](const int &i) const{
        return prefix[i];
    }
    PrefixSum &operator=(const PrefixSum &p)=default;
    PrefixSum &operator=(PrefixSum &&p)=default;
};
bool is_balanced(const PrefixSum &p,const int &n){
    for(int i=1;i<=n;++i){
        if(p[i]==p[n]-p[i]) return true;
    }
    return false;
}

int main(){
    int t;
    std::cin>>t;
    for(int i=0,n;i<t;++i){
        std::cin>>n;
        PrefixSum ps(n);
        for(int j=0,x;j<n;++j){
            std::cin>>x;
            ps.push(x);
        }
        std::cout<<(is_balanced(ps,n)?"Yes\n":"No\n");
    }
}