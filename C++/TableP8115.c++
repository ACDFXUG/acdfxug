#include <iostream>
#include <format>
#include <vector>

std::vector<std::string> get_numbers(std::string_view input){
    std::string num{};
    std::vector<std::string> numbers{};
    for(const char &c:input){
        if(std::isdigit(c)) num+=c;
        else if(!num.empty()){
            numbers.push_back(num);
            num.clear();
        }
    }
    if(!num.empty()) numbers.push_back(num);
    return numbers;
}

std::string fmt(std::vector<std::string> numbers){
    if(numbers.empty()) return "{}";
    std::string output{"{"};
    for(int i=0;i<numbers.size();++i){
        output+=numbers[i];
        output+=(i==numbers.size()-1)?"}":",";
    }
    return output;
}



int main(){
    std::string table;
    std::cin>>table;
    auto numbers=get_numbers(table);
    for(auto &num:numbers){
        auto ull=std::stoull(num);
        auto hex=std::format("0x{:X}",ull);
        if(hex.size()<=num.size()) num=hex;
    }
    std::cout<<fmt(numbers)<<'\n';
}