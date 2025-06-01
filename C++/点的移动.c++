#include <iostream>
#include <algorithm>
#include <vector>

//不是前i个,随便找i个使得最小
int main(){
    int N;
    std::cin>>N;
    std::vector<int> x(N),y(N);
    for(int i=0;i<N;i++){
        std::cin>>x[i]>>y[i];
    }
    for(int i=0;i<N;i++){
        if(i==0) std::cout<<"0\n";
        else{
            auto subx=std::vector(x.begin(),x.begin()+(i+1));
            auto suby=std::vector(y.begin(),y.begin()+(i+1));
            std::sort(subx.begin(),subx.end());
            std::sort(suby.begin(),suby.end());
            int midianx=subx[i/2],midiany=suby[i/2];
            int sum=0;
            for(int j=0;j<=i;j++){
                sum+=std::abs(subx[j]-midianx)+std::abs(suby[j]-midiany);
            }
            std::cout<<sum<<"\n";
        }
    }
}