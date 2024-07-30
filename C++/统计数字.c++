#include <iostream>
#include <map>

int main(){
    int N;
    scanf("%d", &N);
    std::map<int,int> freq;
    while(N-->0){
        int x;
        scanf("%d",&x);
        if(freq.contains(x)){
            freq[x]=1;
        }else{
            freq[x]++;
        }
    }
    for(auto it=freq.begin();it!=freq.end();it++){
        printf("%d %d\n",it->first,it->second);
    }
}