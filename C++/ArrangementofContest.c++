#include <iostream>

int main(){
    int n,ans=0,cnt[26]{};
    std::cin>>n;
    std::string v;
    for(int i=0;i<n;i++){
        std::cin>>v;
        cnt[v[0]-'A']++;
    }
    for(char a='A';a<='Z';a++){
        if(cnt[a-'A']>0){
            ans++;
        }else{
            break;
        }
    }
    printf("%d\n",ans);
}