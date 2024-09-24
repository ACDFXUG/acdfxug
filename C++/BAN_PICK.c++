#include <iostream>
#include <algorithm>

class Survivor{
public:
    std::string name;
    size_t familiarity;
    Survivor():name(""),familiarity(0){}
    Survivor(std::string name,size_t fam):
    name(name),familiarity(fam){}
    bool operator <(const Survivor &svv) const{
        return familiarity>svv.familiarity;
    }
    Survivor &operator =(const Survivor &svv){
        name=svv.name;
        familiarity=svv.familiarity;
        return *this;
    }
};

class Hunter{
public:
    std::string name;
    size_t familiarity;
    Hunter():name(""),familiarity(0){}
    Hunter(std::string name,size_t fam):
    name(name),familiarity(fam){}
    bool operator <(const Hunter &hun) const{
        return familiarity>hun.familiarity;
    }
    Hunter &operator =(const Hunter &hun){
        name=hun.name;
        familiarity=hun.familiarity;
        return *this;
    }
};

int main(){
    std::string name;
    char side;
    size_t fam;
    int svv,htr;
    std::cin>>svv>>htr;
    Survivor *sur=new Survivor[svv];
    Hunter *hnt=new Hunter[htr];
    for(int i=0,s=0,h=0;i<svv+htr;i++){
        std::cin>>name>>side>>fam;
        if(side=='S'){
            sur[s++]={name,fam};
        }else if(side=='H'){
            hnt[h++]={name,fam};
        }
    }
    std::sort(sur,sur+svv);
    std::sort(hnt,hnt+htr);
    printf("%s\n",hnt[2].name.data());
    for(int i=5;i<9;i++){
        printf("%s\n",sur[i].name.data());
    }
    delete[] sur,hnt;
}