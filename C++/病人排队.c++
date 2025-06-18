#include <iostream>
#include <set>

struct Patient{
    int age;
    std::string id;
    int reg_time;
    Patient(int age,std::string id,int reg_time):
    age(age),id(id),reg_time(reg_time){}
    bool operator <(const Patient &p) const{
        if(age>=60&&p.age<60){
            return true;
        }else if(age>=60&&p.age>=60){
            return age==p.age?reg_time<p.reg_time:age>p.age;
        }else if(age<60&&p.age<60){
            return reg_time<p.reg_time;
        }else{
            return false;
        }
    }
};

int main(){
    int n;
    std::cin>>n;
    std::string id;
    std::set<Patient> patients;
    for(int age,reg=1;reg<=n;++reg){
        std::cin>>id>>age;
        patients.emplace(age,id,reg);
    }
    for(const auto &[age,id,reg]:patients){
        std::cout<<id<<'\n';
    }
}