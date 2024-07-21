#include <iostream>
#define String std::string

String reverse(String x,int p,int q){
    String ans=x;
    for(int i=p-1;i<q;i++){
        ans[i]=x[p+q-2-i];
    }
    return ans;
}

int main(){
    String s;
    std::cin>>s;
    int n;
    std::cin>>n;
    while (n--){
        int p,q;
        scanf("%d%d",&p,&q);
        s=reverse(s,p,q);
    }
    std::cout<<s<<std::endl;
}
