#include <iostream>
#include <set>

template<class T>
auto floor(const std::set<T> &set,const T &val){
    auto it=set.lower_bound(val);
    if(it!=set.end()&&*it==val) return it;
    return it==set.begin()?set.end():--it;
}

template<class T>
auto ceiling(const std::set<T> &set,const T &val){
    return set.upper_bound(val);
}

int main(){
    int m;
    std::cin>>m;
    std::set<int> cache;
    for(int act,len;m-->0;){
        std::cin>>act>>len;
        switch(act){
            case 1:{
                if(cache.contains(len)){
                    std::cout<<"Already Exist\n";
                }else{
                    cache.insert(len);
                }
                break;
            }case 2:{
                if(cache.contains(len)){
                    std::cout<<len<<'\n';
                    cache.erase(len);
                }else{
                    if(cache.empty()){
                        std::cout<<"Empty\n";
                    }else{
                        auto shorter=floor(cache,len);
                        auto longer=ceiling(cache,len);
                        if(shorter==cache.end()&&longer!=cache.end()){
                            std::cout<<*longer<<'\n';
                            cache.erase(longer);
                        }else if(shorter!=cache.end()&&longer==cache.end()){
                            std::cout<<*shorter<<'\n';
                            cache.erase(shorter);
                        }else{
                            if(*longer-len<len-*shorter){
                                std::cout<<*longer<<'\n';
                                cache.erase(longer);
                            }else{
                                std::cout<<*shorter<<'\n';
                                cache.erase(shorter);
                            }
                        }
                    }
                }
                break;
            }
        }
    }
}