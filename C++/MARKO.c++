#include <iostream>
#include <unordered_map>
#include <unordered_set>

template<class K,class V>
using hash_map=std::unordered_map<K,V>;

template<class T>
using hash_set=std::unordered_set<T>;

hash_map<char,int> marko{
    {'a',2},{'b',2},{'c',2},
    {'d',3},{'e',3},{'f',3},
    {'g',4},{'h',4},{'i',4},
    {'j',5},{'k',5},{'l',5},
    {'m',6},{'n',6},{'o',6},
    {'p',7},{'q',7},{'r',7},{'s',7},
    {'t',8},{'u',8},{'v',8},
    {'w',9},{'x',9},{'y',9},{'z',9}
};

struct Array{
    int arr[8]{};
    bool operator <=(const Array& rhs) const{
        for(int i=0;i<8;i++){
            if(arr[i]>rhs.arr[i])
                return false;
        }
        return true;
    }
};

Array get_array(const std::string &str){
    Array ans;
    for(auto c:str){
        ans.arr[marko[c]-2]++;
    }
    return ans;
}

Array getArray(const std::string &num){
    Array ans;
    for(auto c:num){
        ans.arr[c-'2']++;
    }
    return ans;
}

int main(){
    int N;
    scanf("%d",&N);
    std::string *markos=new std::string[N];
    Array *arrays=new Array[N];
    for(int i=0;i<N;i++){
        std::cin>>markos[i];
        arrays[i]=get_array(markos[i]);
    }
    std::string num;
    std::cin>>num;
    Array array=getArray(num);
    int ans=0;
    for(int i=0;i<N;i++){
        if(arrays[i]<=array)
            ans++;
    }
    printf("%d\n",ans);
    hash_set<int> ans_set;
}
