#include <iostream> 
#include <vector>
#define Vector std::vector
#define String std::string

template<typename T> 
bool equals(Vector<T> a,Vector<T> b,int l){
    for(int i=0;i<l;i++){
        if(a[i]!=b[i]){
            return false;
        }
    }
    return true;
}

int main(){
    String S,yahoo="yahoo";
    std::cin>>S;  
    Vector<int> c(26,0),h(26,0);
    for(int i=0;i<5;i++){
        c[S[i]-'a']++;
        h[yahoo[i]-'a']++;
    }
    printf(equals(c,h,26)?"YES\n":"NO\n");
}