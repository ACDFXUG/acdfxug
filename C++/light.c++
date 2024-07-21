#include <iostream>
#include <vector>
typedef std::vector<int> VI;

VI powerOfTen(int n){
    VI res(n+1,0);
    res[0] = 1;
    return res;
}

VI lightOn(VI light,int i){
    VI a(i-light.size(),0);
    a.insert(a.end(),light.begin(),light.end());
    return a;
}

VI lightChange(VI light,int i){
    int l=light.size();
    light[l-i-1]=!light[l-i-1];
    return light;
}

int indexOf(VI light,int s){
    for(int i=0;i<light.size();i++){
        if(light[i]==s){
            return i;
        }
    }
    return -1;
}

int main(){
    int n;
    std::cin>>n;
    VI light(1+n,0);
    for(int s=1;s<=n;s++){
        double a;
        int t;
        scanf("%lf%d",&a,&t);
        for(int q=1;q<=t;q++){
            int i=(int)(q*a);
            light=i>=light.size()?
            lightOn(light,i):lightChange(light,i);
        }
    }
    auto p=indexOf(light,1);
    printf("%d\n",light.size()-p-1);
}
