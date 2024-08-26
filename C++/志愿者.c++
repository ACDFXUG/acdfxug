#include <iostream>
#include <algorithm>

class Volunteer{
    public:
    int id,time,diff;
    Volunteer():id(0),time(0),diff(0){}
    Volunteer(int id,int time,int diff):
        id(id),time(time),diff(diff){}
    Volunteer &operator=(const Volunteer &vol){
        id=vol.id;
        time=vol.time;
        diff=vol.diff;
        return *this;
    }
    bool operator<(const Volunteer &vol) const{
        int contri1=time*diff;
        int contri2=vol.time*vol.diff;
        if(contri1==contri2){
            if(time==vol.time){
                return id<vol.id;
            }else{
                return vol.time<time;
            }
        }else{
            return contri2<contri1;
        }
    }
};

int main(){
    int n;
    std::cin>>n;
    Volunteer *vol=new Volunteer[n]();
    for(int i=0,ti,ki;i<n;i++){
        scanf("%d%d",&ti,&ki);
        vol[i]={1+i,ti,ki};
    }
    std::sort(vol,vol+n);
    for(int i=0;i<n;i++){
        printf(i==n-1?"%d\n":"%d ",vol[i].id);
    }
    delete[] vol;
}