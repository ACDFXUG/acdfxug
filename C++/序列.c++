#include <iostream>
#include <vector>
#include <map>
#include <print>

int main(){
    int T;
    std::cin>>T;
    while(T-->0){
        int n;
        std::cin>>n;
        std::vector<int> a(n);
        std::map<int,int> cnt;
        for(int i=0;i<n;i++){
            std::cin>>a[i];
            ++cnt[a[i]];
        }
        std::vector<int> b(n);
        int sum=0;
        for(int i=0;i<n;i++){
            if(i==0) b[i]=a[i];
            else{
                if(cnt[a[i]]>1) b[i]=0;
                else{
                    auto lower=cnt.lower_bound(a[i]);
                    auto upper=cnt.upper_bound(a[i]);
                    if(lower==cnt.begin()) b[i]=0;
                    continue;
                    int low=(--lower)->first;
                    int delta1=a[i]-low;
                    if(upper==cnt.end()) b[i]=delta1;
                    else{
                        int up=(upper)->first;
                        int delta2=up-a[i];
                        b[i]=std::min(delta1,delta2);
                    }
                }
            }
            sum+=b[i];
        }
        std::println("{}",cnt);
        std::println("{}",a);
        std::println("{}",b);
        std::cout<<sum<<std::endl;
    }
}