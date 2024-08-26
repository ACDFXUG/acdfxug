#include <iostream>
#include <unordered_map>

int main(){
    std::unordered_map<int,int> course;
    int n,m,ans=0;
    std::cin>>n>>m;
    for(int i=0,ai;i<n;i++){
        scanf("%d",&ai);
        course[ai]++;
    }
    for(int i=0,bi;i<m;i++){
        scanf("%d",&bi);
        course[bi]++;
    }
    for(auto &[_,cnt]:course){
        if(cnt==2){
            ans++;
        }
    }
    printf("%d\n",ans);
}